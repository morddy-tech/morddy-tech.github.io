export interface Experience {
  role: string;
  organization: string;
  period?: string;
  focus: string[];
  summary: string;
}

export const experience: Experience[] = [
  {
    role: "Founder & Full-Stack Developer",
    organization: "Morddy Global Ltd",
    period: "Current",
    summary:
      "Building and operating a full-stack business platform — application architecture, development, authentication, RBAC, APIs, databases, cybersecurity, and cloud deployment.",
    focus: [
      "Application architecture",
      "Full-stack development",
      "Business platforms",
      "Authentication & RBAC",
      "APIs & databases",
      "Cybersecurity",
      "Cloud & deployment",
    ],
  },
  {
    role: "Administrator",
    organization: "Omni Hotels",
    summary:
      "Managed workflows, records, and technical documentation across departments, strengthening cross-functional communication and organizational systems.",
    focus: ["Workflow management", "Records management", "Technical documentation", "Cross-functional communication"],
  },
  {
    role: "Student Teacher — Mathematics",
    organization: "Spring Leaders Inc",
    summary:
      "Taught mathematics with a focus on analytical thinking and problem solving, incorporating technology-assisted learning methods.",
    focus: ["Analytical thinking", "Communication", "Problem solving", "Technology-assisted learning"],
  },
  {
    role: "Graphic Designer",
    organization: "Veesham Printing Press",
    summary:
      "Delivered client-focused visual work — sharpening client communication, visual hierarchy, and the UI/design principles that now inform product work.",
    focus: ["Client communication", "Visual communication", "UI & design principles", "Branding"],
  },
];