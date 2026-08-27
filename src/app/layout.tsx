import type { Metadata, Viewport } from "next";
import type { ReactNode } from "react";
import { Inter, JetBrains_Mono } from "next/font/google";
import { Navbar } from "@/components/layout/Navbar";
import { Footer } from "@/components/layout/Footer";
import { themeScript } from "@/lib/theme";
import { site } from "@/data/site";
import { cvFileExists } from "@/lib/cv";
import "./globals.css";

const inter = Inter({
  subsets: ["latin"],
  variable: "--font-inter",
  display: "swap",
});

const jetbrainsMono = JetBrains_Mono({
  subsets: ["latin"],
  variable: "--font-jetbrains",
  display: "swap",
});

export const metadata: Metadata = {
  metadataBase: new URL(site.url),
  title: {
    default: "Ifedayo Matthew | Full-Stack Software Developer & Cybersecurity-Focused Engineer",
    template: "%s | Morddy",
  },
  description:
    "Ifedayo Matthew is a Full-Stack Software Developer and Cybersecurity-Focused Engineer specializing in Next.js, React, TypeScript, Node.js, PostgreSQL, AI applications, network security, and secure software development.",
  keywords: [
    "Ifedayo Matthew",
    "Morddy",
    "Full-Stack Developer",
    "Software Engineer",
    "Cybersecurity",
    "Next.js",
    "React",
    "TypeScript",
    "Node.js",
    "PostgreSQL",
  ],
  authors: [{ name: site.name, url: site.url }],
  creator: site.name,
  alternates: {
    canonical: "/",
  },
  openGraph: {
    type: "website",
    locale: "en_US",
    url: site.url,
    siteName: `${site.name} — ${site.brand}`,
    title: "Ifedayo Matthew | Full-Stack Software Developer & Cybersecurity-Focused Engineer",
    description:
      "I build modern, scalable web applications with a security-first mindset — from interfaces and APIs to databases, authentication, cloud infrastructure, and security monitoring.",
    images: [
      {
        url: "/images/branding/og-image.jpg",
        width: 1200,
        height: 630,
        alt: "Ifedayo Matthew — Full-Stack Software Developer & Cybersecurity-Focused Engineer",
      },
    ],
  },
  twitter: {
    card: "summary_large_image",
    title: "Ifedayo Matthew | Full-Stack Software Developer",
    description:
      "Full-Stack Software Developer & Cybersecurity-Focused Engineer — Next.js, React, TypeScript, Node.js, PostgreSQL, and security monitoring.",
    images: ["/images/branding/og-image.jpg"],
  },
  icons: {
    icon: [
      { url: "/images/branding/favicon.ico", sizes: "any" },
      { url: "/images/branding/favicon.png", sizes: "32x32", type: "image/png" },
    ],
    apple: [{ url: "/images/branding/apple-touch-icon.png", sizes: "180x180", type: "image/png" }],
  },
  manifest: "/manifest.webmanifest",
  robots: {
    index: true,
    follow: true,
  },
};

export const viewport: Viewport = {
  themeColor: [
    { media: "(prefers-color-scheme: dark)", color: "#0a0f1c" },
    { media: "(prefers-color-scheme: light)", color: "#f7f9fc" },
  ],
  colorScheme: "light dark",
};

const personJsonLd = {
  "@context": "https://schema.org",
  "@type": "Person",
  name: site.name,
  alternateName: site.brand,
  url: site.url,
  image: `${site.url}/images/profile/profile.jpg`,
  jobTitle: "Full-Stack Software Developer",
  description: site.positioning,
  email: `mailto:${site.email}`,
  telephone: site.phone,
  sameAs: [site.github, site.socials.linkedin],
  knowsAbout: [
    "Next.js",
    "React",
    "TypeScript",
    "Node.js",
    "PostgreSQL",
    "Cybersecurity",
    "Network Security",
    "SIEM",
    "Wazuh",
  ],
};

const websiteJsonLd = {
  "@context": "https://schema.org",
  "@type": "WebSite",
  name: `${site.name} — Portfolio`,
  url: site.url,
  description:
    "Professional portfolio of Ifedayo Matthew (Morddy), Full-Stack Software Developer and Cybersecurity-Focused Engineer.",
  author: { "@type": "Person", name: site.name },
};

export default function RootLayout({ children }: Readonly<{ children: ReactNode }>) {
  const cvAvailable = cvFileExists();
  return (
    <html lang="en" suppressHydrationWarning>
      <head>
        <script dangerouslySetInnerHTML={{ __html: themeScript }} />
      </head>
      <body className={`${inter.variable} ${jetbrainsMono.variable} font-sans antialiased`}>
        <a href="#main" className="skip-link">
          Skip to main content
        </a>
        <Navbar cvAvailable={cvAvailable} />
        <main id="main">{children}</main>
        <Footer />
        <script
          type="application/ld+json"
          dangerouslySetInnerHTML={{ __html: JSON.stringify(personJsonLd) }}
        />
        <script
          type="application/ld+json"
          dangerouslySetInnerHTML={{ __html: JSON.stringify(websiteJsonLd) }}
        />
      </body>
    </html>
  );
}