# maven-parent-poms

Maven parent POMs for managing default plugins and any other active [official Apache Maven ones](
https://maven.apache.org/plugins/). The only exception is `maven-jdeprscan-plugin`, which never made
it out of alpha in 2019 and has not yet been retired (despite its active vulnerabilities).

(Note that a few plugins are very old and likely not useful anymore, but have been pinned regardless
since it will do no harm. Comments are left inline on [maven-parent-simple/pom.xml](
maven-parent-simple/pom.xml)

### `maven-parent-simple`

Simply manages the plugins, pinning them to their latest version.
Can be used to avoid needing to have a thousand Dependabot PRs, etc.

### `maven-parent-opinionated`

Builds on `maven-parent-simple` to provide plugin configuration and executions
to follow/enforce best practices and simple, useful results.

### `test`

Just tests for the above (not particularly useful elsewhere).  TODO

### `maven-parent-poms-reactor`

Build management for this project (not particularly useful elsewhere).
