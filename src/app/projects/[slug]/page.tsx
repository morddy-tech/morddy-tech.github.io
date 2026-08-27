import type { Metadata } from "next";
import type { ReactNode } from "react";
import Link from "next/link";
import { notFound } from "next/navigation";
import { ArrowLeft, Github, ExternalLink, ShieldCheck, CheckCircle2 } from "lucide-react";
import { getProject, projects } from "@/data/projects";
import { site } from "@/data/site";
import { CardImage } from "@/components/shared/CardImage";
import { TechBadge } from "@/components/shared/TechBadge";

interface Props {
  params: Promise<{ slug: string }>;
}

export function generateStaticParams() {
  return projects.map((project) => ({ slug: project.slug }));
}

export async function generateMetadata({ params }: Props): Promise<Metadata> {
  const { slug } = await params;
  const project = getProject(slug);
  if (!project) return {};
  return {
    title: `${project.title} — ${project.category}`,
    description: project.description,
    alternates: { canonical: `/projects/${project.slug}/` },
    openGraph: {
      title: `${project.title} | Ifedayo Matthew`,
      description: project.description,
      type: "article",
      url: `${site.url}/projects/${project.slug}/`,
    },
  };
}

function DetailBlock({
  title,
  children,
}: {
  title: string;
  children: ReactNode;
}) {
  return (
    <section className="tech-panel rounded-xl p-6 sm:p-8">
      <h2 className="font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">{title}</h2>
      <div className="mt-4 text-sm leading-relaxed text-muted sm:text-base">{children}</div>
    </section>
  );
}

export default async function ProjectPage({ params }: Props) {
  const { slug } = await params;
  const project = getProject(slug);
  if (!project) notFound();

  return (
    <article className="container-page pb-20 pt-28 sm:pt-32">
      <Link
        href="/#projects"
        className="inline-flex items-center gap-2 text-sm font-semibold text-muted transition-colors hover:text-accent"
      >
        <ArrowLeft className="h-4 w-4" aria-hidden="true" />
        All projects
      </Link>

      <header className="mt-8">
        <p className="font-mono text-xs uppercase tracking-[0.2em] text-accent">{project.category}</p>
        <h1 className="mt-3 text-3xl font-bold tracking-tight text-balance sm:text-4xl lg:text-5xl">
          {project.title}
        </h1>
        <p className="mt-3 max-w-2xl text-base leading-relaxed text-muted sm:text-lg">{project.description}</p>

        <div className="mt-6 flex flex-wrap items-center gap-3">
          {project.links.map((link) => (
            <a
              key={`${link.label}-${link.href}`}
              href={link.href}
              target="_blank"
              rel="noopener noreferrer"
              className="inline-flex items-center gap-2 rounded-lg border border-line bg-surface px-4 py-2.5 text-sm font-semibold text-foreground transition-colors hover:border-accent hover:text-accent"
            >
              {link.label === "GitHub" ? (
                <Github className="h-4 w-4" aria-hidden="true" />
              ) : (
                <ExternalLink className="h-4 w-4" aria-hidden="true" />
              )}
              {link.label}
            </a>
          ))}
        </div>
      </header>

      <div className="mt-10 overflow-hidden rounded-xl border border-line bg-surface">
        <CardImage
          src={project.image}
          alt={project.imageAlt}
          title={project.title}
          category={project.category}
        />
      </div>

      <div className="mt-10 grid gap-8 lg:grid-cols-3">
        <div className="space-y-8 lg:col-span-2">
          <DetailBlock title="01 · Overview">{project.overview}</DetailBlock>
          <DetailBlock title="02 · Problem">{project.problem}</DetailBlock>
          <DetailBlock title="03 · Solution">{project.solution}</DetailBlock>
          <DetailBlock title="04 · Architecture">{project.architecture}</DetailBlock>

          <section className="tech-panel rounded-xl p-6 sm:p-8">
            <h2 className="font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              05 · Key Features
            </h2>
            <ul className="mt-4 grid gap-3 sm:grid-cols-2">
              {project.features.map((feature) => (
                <li key={feature} className="flex items-start gap-2.5 text-sm leading-relaxed text-muted">
                  <CheckCircle2 className="mt-0.5 h-4 w-4 shrink-0 text-accent" aria-hidden="true" />
                  {feature}
                </li>
              ))}
            </ul>
          </section>

          <DetailBlock title="07 · Challenges">
            <ul className="list-disc space-y-2 pl-5">
              {project.challenges.map((challenge) => (
                <li key={challenge}>{challenge}</li>
              ))}
            </ul>
          </DetailBlock>

          <DetailBlock title="09 · Outcome">{project.outcome}</DetailBlock>
        </div>

        <aside className="space-y-8">
          <section className="tech-panel rounded-xl p-6">
            <h2 className="font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              06 · Technologies
            </h2>
            <ul className="mt-4 flex flex-wrap gap-2">
              {project.tech.map((tech) => (
                <li key={tech}>
                  <TechBadge label={tech} primary />
                </li>
              ))}
            </ul>
          </section>

          <section className="tech-panel rounded-xl p-6">
            <h2 className="inline-flex items-center gap-2 font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              <ShieldCheck className="h-4 w-4" aria-hidden="true" />
              08 · Security Considerations
            </h2>
            <ul className="mt-4 space-y-3">
              {project.security.map((item) => (
                <li key={item} className="text-sm leading-relaxed text-muted">
                  {item}
                </li>
              ))}
            </ul>
          </section>

          <section className="tech-panel rounded-xl p-6">
            <h2 className="font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              Highlights
            </h2>
            <ul className="mt-4 flex flex-wrap gap-2">
              {project.highlight.map((item) => (
                <li
                  key={item}
                  className="rounded-md border border-line bg-surface-2 px-2.5 py-1 font-mono text-[0.7rem] text-muted"
                >
                  {item}
                </li>
              ))}
            </ul>
          </section>

          <section className="tech-panel rounded-xl p-6">
            <h2 className="font-mono text-xs font-medium uppercase tracking-[0.2em] text-accent">
              10–11 · Repository & Demo
            </h2>
            <div className="mt-4 flex flex-col gap-3">
              {project.links.map((link) => (
                <a
                  key={`${link.label}-${link.href}`}
                  href={link.href}
                  target="_blank"
                  rel="noopener noreferrer"
                  className="inline-flex items-center justify-center gap-2 rounded-lg border border-line bg-surface px-4 py-2.5 text-sm font-semibold text-foreground transition-colors hover:border-accent hover:text-accent"
                >
                  {link.label === "GitHub" ? (
                    <Github className="h-4 w-4" aria-hidden="true" />
                  ) : (
                    <ExternalLink className="h-4 w-4" aria-hidden="true" />
                  )}
                  {link.label}
                </a>
              ))}
            </div>
          </section>
        </aside>
      </div>
    </article>
  );
}