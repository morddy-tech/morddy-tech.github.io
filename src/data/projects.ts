export type ProjectCategory =
  | "Full-Stack"
  | "AI"
  | "Cybersecurity"
  | "Frontend"
  | "Backend";

export interface ProjectLink {
  label: string;
  href: string;
}

export interface Project {
  slug: string;
  title: string;
  tagline: string;
  category: string;
  categories: ProjectCategory[];
  featured: boolean;
  shortDescription: string;
  description: string;
  image?: string;
  imageAlt?: string;
  links: ProjectLink[];
  tech: string[];
  highlight: string[];
  overview: string;
  problem: string;
  solution: string;
  architecture: string;
  features: string[];
  security: string[];
  challenges: string[];
  outcome: string;
}

export const projectCategories: Array<ProjectCategory | "All"> = [
  "All",
  "Full-Stack",
  "AI",
  "Cybersecurity",
  "Frontend",
  "Backend",
];

export const projects: Project[] = [
  {
    slug: "studymate-ai",
    title: "StudyMateAI",
    tagline: "AI-powered learning platform",
    category: "AI / Full-Stack Application",
    categories: ["AI", "Full-Stack"],
    featured: true,
    shortDescription:
      "An AI-powered learning platform that transforms study materials — documents, notes, and transcripts — into structured resources: summaries, flashcards, quizzes, and study guides.",
    description:
      "StudyMateAI is an AI-powered learning platform that transforms learning materials into structured study resources. Users upload or paste content and receive AI-generated summaries, simplified explanations, flashcards, quizzes, and study guides — built with a modern Next.js stack and the Google Gemini API.",
    image: undefined,
    links: [
      { label: "Live Demo", href: "https://study-mate-ai-psi.vercel.app" },
      { label: "GitHub", href: "https://github.com/morddy-tech/studymate-ai" },
    ],
    tech: [
      "Next.js",
      "React",
      "TypeScript",
      "Tailwind CSS",
      "Google Gemini API",
      "Document Processing",
      "Vercel",
    ],
    highlight: [
      "AI-generated summaries",
      "Interactive flashcards",
      "Auto-generated quizzes",
      "Document processing & OCR",
      "Audio summaries",
      "Mind maps",
      "Spaced repetition",
      "Responsive UI",
    ],
    overview:
      "StudyMateAI is an AI-powered learning platform that converts raw study materials — lecture notes, textbooks, research papers, and transcripts — into structured learning resources. It is designed to make studying faster and more effective by giving learners summaries, explanations, flashcards, quizzes, and study guides generated from their own content.",
    problem:
      "Learners are often overwhelmed by dense, unstructured study material. Manually summarizing notes, building flashcards, and drafting quizzes is slow and error-prone, and most generic learning tools force users to work within a fixed curriculum rather than their own materials.",
    solution:
      "StudyMateAI accepts a learner's own content and uses the Google Gemini API to produce structured study outputs. The application processes uploaded documents and pasted text, then generates summaries, simplified explanations, flashcards, quizzes, and study guides — with spaced repetition to reinforce retention and audio summaries for on-the-go review.",
    architecture:
      "The application is built with Next.js and React with TypeScript, styled with Tailwind CSS. The frontend handles document upload and content parsing, while the Google Gemini API provides the generative layer. Components are modular (summary, flashcard, quiz, and study-guide modules) and the app is deployed on Vercel.",
    features: [
      "AI-generated summaries and simplified explanations",
      "Flashcards with spaced-repetition review",
      "Auto-generated quizzes with feedback",
      "Document processing and OCR for uploaded materials",
      "Audio summaries for listening-based review",
      "Mind maps that visualize topic structure",
      "Persistent, responsive UI across devices",
    ],
    security: [
      "User-uploaded documents are handled through a constrained, client-side-first pipeline with validation before processing.",
      "Prompt inputs are constructed defensively to reduce prompt-injection surface from untrusted document content.",
      "No long-lived API keys are embedded in the shipped client bundle; provider credentials stay in environment configuration.",
    ],
    challenges: [
      "Mapping variable document formats into consistent structured study outputs.",
      "Splitting large documents into processable chunks while preserving context.",
      "Keeping generated study content accurate and grounded in the source material.",
    ],
    outcome:
      "A working, deployed learning application that demonstrates end-to-end full-stack development, AI integration, product thinking, and a modern component architecture. The project is the primary evidence of AI product engineering in this portfolio.",
  },
  {
    slug: "network-traffic-analyzer",
    title: "Network Traffic Analyzer",
    tagline: "Security monitoring & protocol analysis",
    category: "Cybersecurity / Network Security",
    categories: ["Cybersecurity", "Backend"],
    featured: true,
    shortDescription:
      "A SOC-style network monitoring application that captures packets and analyzes TCP, UDP, ICMP, DNS, and ARP traffic with a security dashboard and authenticated APIs.",
    description:
      "A cybersecurity tool built with Python, Django, and Scapy that performs live packet capture and protocol analysis — TCP, UDP, ICMP, DNS, and ARP — with traffic statistics, a SOC-style monitoring dashboard, and authenticated REST APIs.",
    image: undefined,
    links: [
      {
        label: "GitHub",
        href: "https://github.com/morddy-tech/network-traffic-analyzer",
      },
    ],
    tech: [
      "Python",
      "Django",
      "Scapy",
      "Docker",
      "REST APIs",
      "Database",
      "Linux",
    ],
    highlight: [
      "Live packet capture",
      "TCP / UDP / ICMP / DNS / ARP analysis",
      "Traffic statistics",
      "SOC-style dashboard",
      "Passive anomaly indicators",
      "Authenticated APIs",
      "Packet exploration & reporting",
    ],
    overview:
      "The Network Traffic Analyzer is a network security monitoring application. It captures live traffic, analyzes packets at the protocol level (TCP, UDP, ICMP, DNS, ARP), and presents the results through a SOC-style dashboard with traffic statistics and passive anomaly indicators. All data access is through authenticated REST APIs.",
    problem:
      "Network activity is invisible without tooling. Security teams and learners need a way to observe what is actually traversing a network — which protocols dominate, where anomalies appear, and which hosts are communicating — without relying on opaque commercial appliances.",
    solution:
      "The analyzer wraps Scapy-based packet capture in a Django application. Captured packets are normalized, analyzed per protocol, and stored so they can be explored and reported on. A SOC-style dashboard visualizes live statistics and flags passive anomalies, while an authenticated REST API exposes the same data programmatically.",
    architecture:
      "Python and Django form the application core. Scapy performs packet capture and dissection; analyzed frames are persisted to a database for history, statistics, and reporting. The frontend dashboard consumes the Django REST API. The full application is containerized with Docker for reproducible deployments on Linux.",
    features: [
      "Live packet capture and dissection",
      "Protocol-level analysis: TCP, UDP, ICMP, DNS, ARP",
      "Traffic statistics and host communication summaries",
      "SOC-style monitoring dashboard",
      "Passive anomaly indicators (no active intrusion attempts)",
      "Authenticated REST APIs for programmatic access",
      "Packet exploration and exportable reporting",
    ],
    security: [
      "Packet capture requires elevated privileges and is scoped to the monitoring environment.",
      "API endpoints enforce authentication and authorization before exposing captured data.",
      "Capture data is treated as sensitive — access is role-restricted, and dashboards render data without exposing raw packet payloads by default.",
      "Dockerized deployment isolates the capture process and its dependencies.",
    ],
    challenges: [
      "Normalizing captured frames across heterogeneous protocols into a consistent data model.",
      "Processing high-volume packet streams without dropping data or blocking the application.",
      "Separating meaningful anomaly signals from normal network noise.",
    ],
    outcome:
      "A functioning network monitoring system that demonstrates cybersecurity engineering: packet-level understanding, security monitoring, Python development, and authenticated backend APIs. It is the flagship evidence of the cybersecurity specialization.",
  },
  {
    slug: "morddy-global-platform",
    title: "Morddy Global Platform",
    tagline: "Enterprise full-stack business platform",
    category: "Enterprise Full-Stack Platform",
    categories: ["Full-Stack"],
    featured: true,
    shortDescription:
      "A serious business platform with authentication, role-based access control, a portfolio CMS, secure document vault, bookings, payments, subscriptions, and an admin dashboard.",
    description:
      "The Morddy Global Platform is an enterprise-grade full-stack application that centralizes business operations: authentication, role-based access control, a portfolio CMS, a secure document vault, booking management, payments, subscriptions, and an admin dashboard — built on Next.js, Node.js, PostgreSQL, and Prisma.",
    image: undefined,
    links: [
      {
        label: "GitHub",
        href: "https://github.com/morddy-tech/morddy-global-platform",
      },
    ],
    tech: [
      "Next.js",
      "React",
      "TypeScript",
      "Node.js",
      "PostgreSQL",
      "Prisma",
      "REST APIs",
      "Authentication",
      "RBAC",
      "Docker",
    ],
    highlight: [
      "Authentication & session management",
      "Role-based access control",
      "Portfolio CMS",
      "Secure document vault",
      "Bookings & scheduling",
      "Payments & subscriptions",
      "Admin dashboard",
      "Database architecture",
    ],
    overview:
      "The Morddy Global Platform is a full-stack business platform that consolidates the operations of a services business into one application: secure accounts, a portfolio CMS, a document vault, bookings, payments, subscriptions, and an admin dashboard with role-based access control.",
    problem:
      "Running a services business typically means juggling separate tools — a website, a booking system, payment links, file storage, and admin panels — each with its own login, data model, and security posture. That fragmentation creates risk and overhead.",
    solution:
      "The platform brings these workflows into a single system with one authentication layer and one data model. Role-based access control ensures clients, staff, and administrators each see only what they are authorized to see — from portfolio content management to bookings, payments, and the document vault.",
    architecture:
      "A Next.js application on a Node.js backend with PostgreSQL as the database, accessed through Prisma's type-safe ORM. REST APIs expose business logic; authentication and RBAC guard every resource; Docker is used to standardize the runtime environment.",
    features: [
      "Authentication with session management",
      "Role-based access control across client, staff, and admin roles",
      "Portfolio CMS for publishing and editing content",
      "Secure document vault with access controls",
      "Booking and scheduling workflows",
      "Payments and subscription management",
      "Admin dashboard with operational views",
      "Relational database schema designed for business workflows",
    ],
    security: [
      "All business resources are guarded by authentication and role-based authorization.",
      "Passwords are hashed; sessions are managed server-side.",
      "The document vault enforces per-document access rules.",
      "Database access goes through Prisma's parameterized queries to prevent SQL injection.",
      "Dockerized deployments isolate the runtime and simplify dependency hygiene.",
    ],
    challenges: [
      "Modeling the full business workflow — from booking to payment to delivery — in one coherent schema.",
      "Designing RBAC so the same data model safely serves clients, staff, and administrators.",
      "Keeping the platform maintainable as features were added to a single codebase.",
    ],
    outcome:
      "A production-oriented platform that demonstrates enterprise-grade full-stack engineering: database design, authentication, RBAC, API development, and business workflow modeling in a single application.",
  },
  {
    slug: "grist",
    title: "GRIST",
    tagline: "AI personal intelligence system",
    category: "AI / Personal Intelligence System",
    categories: ["AI"],
    featured: true,
    shortDescription:
      "An AI assistant architecture built around agents, memory, knowledge management, and reasoning workflows with a modular design.",
    description:
      "GRIST is an AI personal intelligence system: an assistant architecture composed of modular AI agents with memory, knowledge management, reasoning, and learning workflows, designed to be extended rather than replaced.",
    image: undefined,
    links: [
      { label: "GitHub", href: "https://github.com/morddy-tech/grist" },
    ],
    tech: [
      "Python",
      "AI Agents",
      "LLM APIs",
      "Memory Systems",
      "Knowledge Management",
      "Modular Architecture",
    ],
    highlight: [
      "Modular AI agents",
      "Persistent memory",
      "Knowledge management",
      "Reasoning workflows",
      "Learning workflows",
      "Extensible architecture",
    ],
    overview:
      "GRIST is an AI personal intelligence system. Instead of a single monolithic prompt, it organizes capability into modular agents that share memory and knowledge — enabling reasoning workflows and structured learning over time. The architecture is deliberately modular so new agents and capabilities can be added without rewriting the system.",
    problem:
      "Single-prompt AI assistants forget context, cannot manage knowledge across sessions, and are hard to extend. A personal intelligence system needs memory, structure, and a clear architecture for growth.",
    solution:
      "GRIST separates concerns into distinct agents — retrieval, reasoning, memory, and task execution — coordinated through a shared memory and knowledge layer. This design supports context-aware conversations, knowledge accumulation, and learning workflows while keeping each module replaceable.",
    architecture:
      "GRIST is built around modular agents connected to a shared memory and knowledge store. Each agent owns a narrow responsibility and communicates through defined interfaces, so the system can be extended with new agents and learning workflows without coupling.",
    features: [
      "Modular agent architecture with defined interfaces",
      "Persistent memory across sessions",
      "Knowledge management and retrieval",
      "Reasoning workflows for multi-step tasks",
      "Structured learning workflows",
      "Extensible design for new capabilities",
    ],
    security: [
      "Memory and knowledge stores are treated as sensitive user data with restricted access.",
      "Prompt boundaries between agents are designed to contain prompt-injection risk from external content.",
      "The modular architecture keeps security-sensitive capabilities isolated from general-purpose agents.",
    ],
    challenges: [
      "Designing an agent coordination model that is flexible without becoming unpredictable.",
      "Managing memory so it accumulates knowledge without accumulating noise.",
      "Keeping modules decoupled while sharing a common knowledge layer.",
    ],
    outcome:
      "An evolving AI systems project that demonstrates agentic architecture, memory and knowledge design, and structured reasoning — evidence of the ability to build AI-powered products beyond simple API wrappers.",
  },
  {
    slug: "portfolio",
    title: "This Portfolio",
    tagline: "Personal brand & web engineering",
    category: "Personal Brand / Web Development",
    categories: ["Frontend", "Full-Stack"],
    featured: true,
    shortDescription:
      "The website you are viewing — a production-grade Next.js application engineered for performance, accessibility, SEO, and security.",
    description:
      "This portfolio is itself a project: a production-grade Next.js application built with TypeScript and Tailwind CSS, engineered for performance, accessibility (WCAG 2.2 AA), SEO, and security — deployed as a static export.",
    image: undefined,
    links: [
      {
        label: "GitHub",
        href: "https://github.com/morddy-tech/morddy-tech.github.io",
      },
    ],
    tech: ["Next.js", "React", "TypeScript", "Tailwind CSS", "Lucide", "Static Export"],
    highlight: [
      "Next.js App Router",
      "TypeScript throughout",
      "Static export for GitHub Pages",
      "WCAG 2.2 AA accessibility",
      "Structured data & SEO",
      "Dark / light themes",
      "Performance-first",
    ],
    overview:
      "This website is built the way I would build a production web application: typed end to end, component-driven, accessible, SEO-structured, and performance-conscious — then exported as static files that deploy anywhere.",
    problem:
      "A portfolio is often judged like a product: does it load fast, work on every device, rank in search, and stand up to inspection? A template site answers none of those questions.",
    solution:
      "The portfolio is engineered as a real application: structured data files drive content, semantic components handle layout, and the design system supports dark and light themes, reduced-motion preferences, keyboard navigation, and fast static rendering.",
    architecture:
      "Next.js App Router with TypeScript and Tailwind CSS v4. Content lives in typed data modules rather than hardcoded components. The site is statically exported for GitHub Pages, with per-route metadata, JSON-LD structured data, a sitemap, and an Open Graph preview image.",
    features: [
      "Component-driven architecture with typed content data",
      "Dark / light themes with system preference detection",
      "Keyboard-navigable with visible focus states",
      "Project filtering and detail pages",
      "Accessible contact form with validation",
      "Static export deployable to GitHub Pages or Vercel",
    ],
    security: [
      "No secrets or API keys in client code",
      "Safe external links (rel=\"noopener noreferrer\")",
      "Content Security Policy-compatible, dependency-light build",
      "Contact form avoids third-party data sinks",
    ],
    challenges: [
      "Delivering a premium visual identity without heavy animation frameworks.",
      "Keeping the static export small, fast, and dependency-light.",
      "Meeting accessibility targets across themes and layouts.",
    ],
    outcome:
      "A live, production-quality personal brand site that demonstrates frontend engineering, accessibility practice, SEO, and design system discipline — continuously updated as the portfolio grows.",
  },
  {
    slug: "advanced-calculator",
    title: "Advanced Scientific Calculator",
    tagline: "Desktop productivity application",
    category: "Backend / Desktop Application",
    categories: ["Backend"],
    featured: false,
    shortDescription:
      "A fully-featured scientific calculator built with Python and Tkinter — implemented without eval() for security and validation.",
    description:
      "A desktop scientific calculator supporting arithmetic, trigonometry, logarithms, exponents, roots, factorials, memory operations, constants, answer recall, and a persistent history panel — built with Python and Tkinter.",
    image: "/images/projects/adv_calc.png",
    imageAlt: "Advanced Scientific Calculator interface",
    links: [
      {
        label: "GitHub",
        href: "https://github.com/morddy-tech/morddy-tech.github.io/blob/main/projects/T2-Python/calculator-project/AdvancedCalcGUI.py",
      },
    ],
    tech: ["Python", "Tkinter", "Object-Oriented Design"],
    highlight: [
      "Arithmetic & scientific functions",
      "Memory operations",
      "History panel",
      "Keyboard-friendly UI",
      "No eval() — safe parsing",
    ],
    overview:
      "A desktop productivity tool demonstrating Python engineering: a scientific calculator with a keyboard-friendly Tkinter interface, memory operations, constants, answer recall, and a persistent history panel.",
    problem:
      "Naive calculators use eval() on user input — a well-known injection risk. Building one the right way requires a real parsing and validation strategy.",
    solution:
      "Every operation is implemented with explicit parsing and validated functions — no eval() — with input validation at the boundary and a clean object-oriented structure.",
    architecture:
      "Python 3 with Tkinter for the interface and the standard math library for computation. Object-oriented design separates the calculation engine from the UI layer.",
    features: [
      "Basic arithmetic, trigonometry, logarithms, exponents, roots, factorial",
      "Memory operations (M+, M-, MR, MC) and constants (π, e)",
      "Answer recall (ANS) and persistent history",
      "Keyboard shortcuts and responsive layout",
      "Secure input handling without eval()",
    ],
    security: ["No eval()-based evaluation — all input is validated and parsed explicitly."],
    challenges: ["Implementing expression parsing that is both correct and injection-safe."],
    outcome:
      "A complete, tested desktop application that demonstrates Python, GUI engineering, and security-conscious implementation of a classic tool.",
  },
  {
    slug: "student-management-system",
    title: "Student Management System",
    tagline: "Academic administration desktop application",
    category: "Backend / Desktop Application",
    categories: ["Backend"],
    featured: false,
    shortDescription:
      "A Java Swing desktop application for academic administration with secure data handling, OOP design, and robust validation.",
    description:
      "A comprehensive Java-based desktop application featuring a Swing GUI for academic administration — courses, students, grades, and enrollment — with secure data handling and robust input validation.",
    image: "/images/projects/gui.png",
    imageAlt: "Student Management System interface",
    links: [
      {
        label: "GitHub",
        href: "https://github.com/morddy-tech/morddy-tech.github.io/blob/main/projects/T3-Java/studentManagementSystemGUI/src/studentManagementSystemGUI/StudentManagementSystemGUI.java",
      },
    ],
    tech: ["Java", "Java Swing", "AWT", "File I/O"],
    highlight: [
      "Swing-based GUI",
      "Course & enrollment management",
      "Grade tracking & reporting",
      "Object-oriented design",
      "Input validation",
    ],
    overview:
      "A Java desktop application for academic administration: student records, courses, enrollments, grades, and attendance behind a Swing GUI, with file-based persistence and rigorous input validation.",
    problem:
      "Administrative record-keeping in small institutions is often manual or spreadsheet-based, with no validation and no audit trail.",
    solution:
      "The system centralizes records in structured files with an object-oriented domain model, validating every input to prevent data corruption and generating student reports.",
    architecture:
      "Java with Swing and AWT for the interface. The domain model (Student, Course, Enrollment, Grade, Attendance) is object-oriented, with data persisted to structured files.",
    features: [
      "Student, course, and enrollment management",
      "Grades, attendance, and report generation",
      "Secure data handling with validated input",
      "Object-oriented architecture and reusable modules",
    ],
    security: ["Robust input validation prevents malformed or corrupt data from entering the system."],
    challenges: ["Designing a domain model clean enough to support reporting across several data files."],
    outcome:
      "A functional administration system demonstrating Java engineering, object-oriented design, and data integrity practices.",
  },
  {
    slug: "vehicle-information-system",
    title: "Vehicle Information System",
    tagline: "Java OOP console application",
    category: "Backend / Console Application",
    categories: ["Backend"],
    featured: false,
    shortDescription:
      "A modular vehicle management system for a rental agency — interfaces, inheritance, encapsulation, and type-safe enums in Java.",
    description:
      "A modular vehicle management system for a car rental agency implementing OOP principles — interfaces, inheritance, encapsulation — with type-safe enums, professional error handling, and reusable validation.",
    image: "/images/projects/vehInfo.png",
    imageAlt: "Vehicle Information System interface",
    links: [
      {
        label: "GitHub",
        href: "https://github.com/morddy-tech/morddy-tech.github.io/blob/main/projects/T3-Java/vehicleInfoSystem/src/com/vehicleInfoSystem/VehicleInformationSystem.java",
      },
    ],
    tech: ["Java", "OOP", "Enums", "Input Validation"],
    highlight: [
      "Interfaces & inheritance",
      "Encapsulation",
      "Type-safe enums",
      "Reusable input utilities",
      "Error handling",
    ],
    overview:
      "A console application for managing cars, motorcycles, and trucks in a rental agency — a demonstration of clean object-oriented design in Java.",
    problem:
      "Vehicle records in rental agencies drift between spreadsheets and ad-hoc tools; the domain is a perfect candidate for a well-modeled object-oriented system.",
    solution:
      "The system models vehicles with inheritance and interfaces (MotorVehicle, Car, Motorcycle, Truck), uses type-safe enums for fuel and transmission, and validates all input with reusable utilities.",
    architecture:
      "Java with a layered object model: base abstractions, concrete vehicle types, and a console interface with reusable validation utilities.",
    features: [
      "Enter and display cars, motorcycles, and trucks",
      "Inheritance, interfaces, and encapsulation",
      "Type-safe enums for fuel, transmission, and vehicle type",
      "Range checks and professional error handling",
    ],
    security: ["Custom validation prevents malformed input and keeps records consistent."],
    challenges: ["Balancing a clean inheritance hierarchy with the different attributes of each vehicle type."],
    outcome:
      "A working console system demonstrating Java, object-oriented design, and disciplined input handling.",
  },
];

export function getProject(slug: string): Project | undefined {
  return projects.find((p) => p.slug === slug);
}
