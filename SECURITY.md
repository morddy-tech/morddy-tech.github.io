# Security Policy

This portfolio is a static site with no server-side components, no database, and no user data storage. Security considerations for this repository:

## Safe Harbor

We take the security of this project seriously. If you believe you have found a security issue in this website, please report it privately:

### GitHub Private Vulnerability Reporting (Preferred)
Navigate to the **Security** tab of this repository and click **Report a vulnerability**.

### Email
If you cannot use GitHub's reporting tool, email **graxuluke@gmail.com** with `[Security]` in the subject line.

### What to Expect
- **Acknowledgement:** You will receive a response within 48 hours.
- **Status Updates:** Updates at least once every 7 days until the issue is resolved.

## Deployed Environment Notes

- The site is a static export: no API routes, no secrets, no environment variables required at runtime.
- The contact form opens a pre-filled email draft client-side; it stores no data and exposes no keys.
- Third-party links use `rel="noopener noreferrer"`.
- Dependency hygiene is maintained through `npm ci` + lockfile; CI runs lint, typecheck, and build on every push.