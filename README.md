WIP

# maven-parent-poms

Maven parent POMs for managing default plugins and any other active [official Apache Maven ones](
https://maven.apache.org/plugins/).

### `maven-parent-simple`

Simply manages the plugins, pinning them to their latest version.
Can be used to avoid needing to have a thousand Dependabot PRs, etc.

(Note that a few plugins are very old and likely not useful anymore, but have been pinned regardless
since it will do no harm. Comments are left inline on [maven-parent-simple/pom.xml](
maven-parent-simple/pom.xml).)

### `maven-parent-opinionated` (Recommended)

Builds on `maven-parent-simple` to provide plugin configuration and executions
to follow/enforce best practices and provide simple, useful results.

### `test`

Just tests for the above (not particularly useful elsewhere).

### `maven-parent-poms-reactor`

Build management for this project (not particularly useful elsewhere).
