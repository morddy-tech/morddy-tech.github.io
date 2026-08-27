export interface Skill {
  name: string;
  primary?: boolean;
}

export interface SkillCategory {
  label: string;
  description: string;
  skills: Skill[];
}

export const skillCategories: SkillCategory[] = [
  {
    label: "Frontend",
    description: "Modern interfaces, typed components, and design systems.",
    skills: [
      { name: "Next.js", primary: true },
      { name: "React", primary: true },
      { name: "TypeScript", primary: true },
      { name: "JavaScript", primary: true },
      { name: "HTML" },
      { name: "CSS" },
      { name: "Tailwind CSS" },
    ],
  },
  {
    label: "Backend",
    description: "APIs, services, and server-side logic.",
    skills: [
      { name: "Node.js", primary: true },
      { name: "REST APIs", primary: true },
      { name: "Python", primary: true },
      { name: "Django" },
      { name: "Authentication" },
      { name: "RBAC" },
    ],
  },
  {
    label: "Database",
    description: "Data modeling and persistence.",
    skills: [
      { name: "PostgreSQL", primary: true },
      { name: "Prisma", primary: true },
      { name: "SQL" },
    ],
  },
  {
    label: "DevOps / Cloud",
    description: "Deployment, containers, and infrastructure.",
    skills: [
      { name: "Docker", primary: true },
      { name: "Git", primary: true },
      { name: "GitHub", primary: true },
      { name: "AWS" },
      { name: "Vercel" },
      { name: "CI/CD" },
      { name: "Linux" },
    ],
  },
  {
    label: "Cybersecurity",
    description: "Monitoring, detection, and defense.",
    skills: [
      { name: "Wazuh", primary: true },
      { name: "SIEM" },
      { name: "Threat Detection" },
      { name: "Network Security" },
      { name: "Linux" },
      { name: "Packet Analysis" },
      { name: "Cisco Packet Tracer" },
    ],
  },
  {
    label: "Design",
    description: "Visual systems and product design.",
    skills: [
      { name: "Figma" },
      { name: "Adobe Photoshop" },
      { name: "Illustrator" },
      { name: "UI/UX" },
    ],
  },
];