# maven-parent-poms

Maven parent POMs for managing default plugins (i.e., the official Apache Maven ones).

### `maven-parent-simple`

Simply manages the plugins, pinning them to their latest version.
Can be used to avoid needing to have a thousand Dependabot PRs, etc.

### `maven-parent-opinionated`

Builds on `maven-parent-simple` to provide plugin configuration and executions
to follow/enforce best practices and simple, useful results.

### `test`

Just tests for the above (not particularly useful elsewhere).  TODO
