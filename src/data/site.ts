export const site = {
  name: "Ifedayo Matthew",
  brand: "Morddy",
  title: "Full-Stack Software Developer",
  secondaryTitle: "Cybersecurity-Focused Engineer",
  positioning:
    "I build modern, scalable web applications with a security-first mindset — from intuitive interfaces and robust APIs to databases, authentication, cloud infrastructure, and security monitoring.",
  email: "ifedmord5194@gmail.com",
  phone: "+234 901 370 3764",
  phoneHref: "tel:+2349013703764",
  github: "https://github.com/morddy-tech",
  githubUser: "morddy-tech",
  location: "Nigeria",
  availability: {
    label: "Open to Opportunities",
    detail: "Available for full-time software engineering and cybersecurity roles · Remote / Hybrid / Onsite",
  },
  cvPath: "/Ifedayo-Matthew-CV.pdf",
  url: "https://morddy-tech.github.io",
  socials: {
    github: "https://github.com/morddy-tech",
    email: "mailto:ifedmord5194@gmail.com",
    linkedin: "https://www.linkedin.com/in/ifedayo-matthew",
  },
} as const;

export type Site = typeof site;
