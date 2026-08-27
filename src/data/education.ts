export interface Education {
  degree: string;
  institution: string;
  period: string;
  detail?: string;
}

export const education: Education[] = [
  {
    degree: "BSc Computer Science",
    institution: "University of the People",
    period: "2025 — Present",
    detail: "Pursuing a Computer Science degree with focus on software engineering, algorithms, and data structures.",
  },
  {
    degree: "National Diploma in Computer Science",
    institution: "Federal Polytechnic, Auchi",
    period: "2023 — 2025",
    detail: "National Diploma program covering programming, databases, and computer systems.",
  },
];