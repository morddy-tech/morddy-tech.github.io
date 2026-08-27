export interface Certification {
  name: string;
  provider?: string;
  note?: string;
}

export const certifications: Certification[] = [
  {
    name: "Graphic Design Certification",
    note: "Certification in graphic design covering visual communication, branding, and design principles.",
  },
  {
    name: "Full-Stack Development Training",
    provider: "IBM SkillsBuild · TS Academy",
    note: "Training in frontend and backend integration with cybersecurity best practices.",
  },
];