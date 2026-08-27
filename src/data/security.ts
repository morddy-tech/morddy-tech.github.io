export const securityFocus = [
  "Threat Detection",
  "SIEM",
  "Wazuh",
  "Network Monitoring",
  "Network Security",
  "Authentication",
  "Access Control",
  "Linux Security",
  "Packet Analysis",
  "Security Monitoring",
];

export const securityWorkflow = [
  { step: "Application", detail: "Secure code, input validation, and dependency hygiene" },
  { step: "Authentication", detail: "Identity verification, sessions, and access control" },
  { step: "API", detail: "Authorized endpoints and validated request handling" },
  { step: "Database", detail: "Parameterized queries and least-privilege access" },
  { step: "Monitoring", detail: "Logs, SIEM ingestion, and Wazuh agents" },
  { step: "Detection", detail: "Alerting and response for anomalous activity" },
] as const;

export const securityStatement =
  "Building software is not only about making applications work. It is also about understanding how applications, identities, networks, and infrastructure can be protected.";