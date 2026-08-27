# Morddy — Professional Portfolio

**Ifedayo Matthew (Morddy)** — Full-Stack Software Developer & Cybersecurity-Focused Engineer

A premium, production-ready personal brand website built with Next.js, TypeScript, and Tailwind CSS. The site is statically exported and deployable to GitHub Pages or Vercel.

> I build modern, scalable web applications with a security-first mindset — from intuitive interfaces and robust APIs to databases, authentication, cloud infrastructure, and security monitoring.

## Stack

- **Framework:** Next.js 15 (App Router) · static export
- **Language:** TypeScript (strict)
- **Styling:** Tailwind CSS v4 with dark/light theme tokens
- **Icons:** Lucide
- **Animations:** Lightweight CSS + IntersectionObserver (no animation framework, `prefers-reduced-motion` respected)
- **Fonts:** Inter + JetBrains Mono (self-hosted via `next/font`)

## Quick Start

```bash
npm install
npm run dev        # local development
npm run build      # production build -> ./out
npm run lint       # eslint
npm run typecheck  # tsc --noEmit
```

## Deployment

### GitHub Pages (recommended, current hosting)

The repository includes `.github/workflows/deploy.yml` which builds the site and publishes the `out/` directory via GitHub Actions. Configure your repository's Pages settings to use "GitHub Actions" as the source.

### Vercel

Import the repository and set framework preset to Next.js. Output mode can stay at default; the site works either as static export or standard build.

## Content

All site content lives in typed data modules — no content is hardcoded in components:

| File | Purpose |
| --- | --- |
| `src/data/site.ts` | Name, brand, contact details, socials, CV path |
| `src/data/projects.ts` | Featured + supporting projects (cards and detail pages) |
| `src/data/skills.ts` | Categorized technology stack |
| `src/data/experience.ts` | Work history timeline |
| `src/data/education.ts` | Education records |
| `src/data/certifications.ts` | Verified certifications/training |
| `src/data/security.ts` | Security focus areas + workflow |

## Assets

| Path | Purpose |
| --- | --- |
| `public/images/profile/` | Profile photo (hero + Person schema image) |
| `public/images/branding/` | `favicon.ico`, `favicon.png`, `apple-touch-icon.png`, `og-image.jpg` |
| `public/images/projects/` | Project screenshots |
| `public/Ifedayo-Matthew-CV.pdf` | CV — **add your PDF here** |

Branding placeholders (monogram favicon, apple icon, and Open Graph image) can be regenerated with `powershell -File scripts/generate-branding.ps1` or simply replaced with your own files — paths stay identical, no code changes needed.

The CV button auto-detects the file at build time: if the PDF is present it renders **Download CV**; otherwise it falls back to **Request CV** (mailto) so the site never has a broken link.

### Project links

Featured projects use clearly marked placeholder repository URLs in `src/data/projects.ts` (e.g. `github.com/morddy-tech/studymate-ai`). Replace them with the real repository URLs when they exist.

## Contact Form

The contact form validates input client-side and opens a pre-filled email draft (no third-party service, no API keys, no data storage). Hidden honeypot field + minimum-submit-time provide practical spam protection.

## Notes

- Default theme is dark; light theme is fully supported. The toggle persists to `localStorage` and respects system preference on first visit.
- WCAG 2.2 AA-oriented: semantic landmarks, keyboard navigation, visible focus, aria states, reduced-motion support, descriptive alt text.
- The legacy vanilla HTML/CSS site was fully replaced by this rebuild; the `projects/` folder (coursework source code) is preserved because project pages link to it on GitHub.