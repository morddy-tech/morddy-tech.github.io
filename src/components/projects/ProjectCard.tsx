import Link from "next/link";
import { ArrowUpRight, Github, ExternalLink } from "lucide-react";
import type { Project } from "@/data/projects";
import { CardImage } from "@/components/shared/CardImage";
import { TechBadge } from "@/components/shared/TechBadge";

export function ProjectCard({ project }: { project: Project }) {
  return (
    <article className="group flex h-full flex-col overflow-hidden rounded-xl border border-line bg-surface transition-all duration-300 hover:-translate-y-1 hover:border-accent/50 hover:shadow-xl hover:shadow-black/10">
      <Link
        href={`/projects/${project.slug}/`}
        aria-label={`${project.title} — view project details`}
        className="relative block aspect-video overflow-hidden border-b border-line"
      >
        <CardImage
          src={project.image}
          alt={project.imageAlt}
          title={project.title}
          category={project.category}
        />
        <span
          className="absolute inset-0 bg-gradient-to-t from-black/30 to-transparent opacity-0 transition-opacity duration-300 group-hover:opacity-100"
          aria-hidden="true"
        />
      </Link>

      <div className="flex flex-1 flex-col p-5 sm:p-6">
        <p className="font-mono text-xs uppercase tracking-[0.18em] text-accent">{project.category}</p>
        <h3 className="mt-2 text-lg font-bold tracking-tight text-foreground">{project.title}</h3>
        <p className="mt-2 flex-1 text-sm leading-relaxed text-muted">{project.shortDescription}</p>

        <ul className="mt-4 flex flex-wrap gap-1.5">
          {project.tech.slice(0, 5).map((tech) => (
            <li key={tech}>
              <TechBadge label={tech} />
            </li>
          ))}
        </ul>

        <div className="mt-5 flex flex-wrap items-center gap-4 border-t border-line pt-4">
          {project.links.map((link) => (
            <a
              key={`${link.label}-${link.href}`}
              href={link.href}
              target="_blank"
              rel="noopener noreferrer"
              className="inline-flex items-center gap-1.5 text-sm font-semibold text-foreground transition-colors hover:text-accent"
            >
              {link.label === "GitHub" ? (
                <Github className="h-4 w-4" aria-hidden="true" />
              ) : (
                <ExternalLink className="h-4 w-4" aria-hidden="true" />
              )}
              {link.label}
            </a>
          ))}
          <Link
            href={`/projects/${project.slug}/`}
            className="ml-auto inline-flex items-center gap-1 text-sm font-semibold text-accent transition-colors hover:text-accent-strong"
          >
            Details
            <ArrowUpRight className="h-4 w-4" aria-hidden="true" />
          </Link>
        </div>
      </div>
    </article>
  );
}