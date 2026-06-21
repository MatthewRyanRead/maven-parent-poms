package tech.readonly.maven_parent_poms;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.stream.Collectors.*;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@SuppressWarnings({"java:S6204", "resource"})
class PluginPinTest {
    private static final Pattern PROPERTY_REF = Pattern.compile("\\$\\{([^}]+)}");

    @Test
    void pluginVersionsMatchArtifactIds() throws Exception {
        final Path root = Paths.get("..").toRealPath();
        final Set<Path> poms =
                Files.walk(root)
                        .filter(
                                p ->
                                        p.getFileName().toString().equals("pom.xml")
                                                && !p.toString().contains("/target/")
                                                && !p.toString().contains("/.git/"))
                        .collect(toSet());
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();

        final List<String> mismatches =
                poms.stream()
                        .flatMap(
                                pom -> {
                                    final Document doc;
                                    try {
                                        doc = builder.parse(pom.toFile());
                                    } catch (final Exception e) {
                                        throw new IllegalStateException(e);
                                    }
                                    return findMismatches(
                                            root, pom, doc.getElementsByTagName("plugin"));
                                })
                        .collect(toList());

        assertTrue(
                mismatches.isEmpty(),
                "Pin property mismatches:\n - " + String.join("\n - ", mismatches));
    }

    private static Stream<String> findMismatches(
            final Path root, final Path pom, final NodeList nodes) {
        return IntStream.range(0, nodes.getLength())
                .mapToObj(i -> (Element) nodes.item(i))
                .filter(
                        el -> {
                            final String artifactId = getChildText(el, "artifactId");
                            final String version = getChildText(el, "version");
                            final Matcher m = PROPERTY_REF.matcher(version);
                            return !m.matches() || !m.group(1).equals(artifactId + ".version");
                        })
                .map(
                        el ->
                                root.relativize(pom)
                                        + ": "
                                        + getChildText(el, "artifactId")
                                        + " -> "
                                        + getChildText(el, "version"));
    }

    private static String getChildText(final Element parent, final String name) {
        return IntStream.range(0, parent.getChildNodes().getLength())
                .mapToObj(i -> parent.getChildNodes().item(i))
                .filter(n -> n.getNodeName().equals(name))
                .findFirst()
                .map(n -> n.getTextContent().trim())
                .orElse("");
    }
}
