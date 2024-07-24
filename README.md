# maven-parent-poms

Maven parent POMs for managing default plugins.

### `maven-parent-simple`

Simply manages the official Maven plugins, pinning them to their latest version.
Can be used to avoid needing to have a thousand Dependabot PRs, etc.

### `maven-parent-opinionated`

Builds on `maven-parent-simple` to provide plugin configuration and executions
to follow/enforce best practices and simple, useful results.
