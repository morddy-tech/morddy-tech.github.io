"use client";

import { useState, type FormEvent } from "react";
import { Send, CheckCircle2, AlertCircle, Loader2 } from "lucide-react";
import { site } from "@/data/site";

type Status = "idle" | "sending" | "success" | "error";

interface FormErrors {
  name?: string;
  email?: string;
  subject?: string;
  message?: string;
}

export function ContactForm() {
  const [status, setStatus] = useState<Status>("idle");
  const [errors, setErrors] = useState<FormErrors>({});

  function validate(form: FormData): FormErrors {
    const next: FormErrors = {};
    const name = String(form.get("name") ?? "").trim();
    const email = String(form.get("email") ?? "").trim();
    const subject = String(form.get("subject") ?? "").trim();
    const message = String(form.get("message") ?? "").trim();

    if (name.length < 2) next.name = "Please enter your name.";
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/.test(email)) next.email = "Please enter a valid email address.";
    if (subject.length < 3) next.subject = "Please add a short subject.";
    if (message.length < 10) next.message = "Please write a message of at least 10 characters.";
    return next;
  }

  function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    const form = e.currentTarget;
    const formData = new FormData(form);

    const honeypot = String(formData.get("company_website") ?? "");
    if (honeypot !== "") return;

    const submittedAt = Number(formData.get("submitted_at") ?? 0);
    if (Date.now() - submittedAt < 2500) return;

    const nextErrors = validate(formData);
    setErrors(nextErrors);
    if (Object.keys(nextErrors).length > 0) {
      setStatus("error");
      return;
    }

    setStatus("sending");
    const name = String(formData.get("name")).trim();
    const email = String(formData.get("email")).trim();
    const subject = String(formData.get("subject")).trim();
    const message = String(formData.get("message")).trim();

    const body = `Name: ${name}\nEmail: ${email}\n\n${message}`;
    const mailto = `mailto:${site.email}?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;

    window.setTimeout(() => {
      window.location.href = mailto;
      setStatus("success");
    }, 350);
  }

  const inputClass =
    "w-full rounded-lg border border-line bg-surface px-4 py-2.5 text-sm text-foreground placeholder:text-muted/60 transition-colors focus:border-accent";

  return (
    <form onSubmit={handleSubmit} noValidate className="space-y-5">
      <div className="grid gap-5 sm:grid-cols-2">
        <div>
          <label htmlFor="cf-name" className="mb-1.5 block text-sm font-medium text-foreground">
            Name
          </label>
          <input id="cf-name" name="name" type="text" autoComplete="name" required placeholder="Your name" className={inputClass} aria-invalid={Boolean(errors.name)} aria-describedby={errors.name ? "cf-name-error" : undefined} />
          {errors.name ? (
            <p id="cf-name-error" className="mt-1.5 text-xs text-red-500 light:text-red-600">{errors.name}</p>
          ) : null}
        </div>
        <div>
          <label htmlFor="cf-email" className="mb-1.5 block text-sm font-medium text-foreground">
            Email
          </label>
          <input id="cf-email" name="email" type="email" autoComplete="email" required placeholder="you@example.com" className={inputClass} aria-invalid={Boolean(errors.email)} aria-describedby={errors.email ? "cf-email-error" : undefined} />
          {errors.email ? (
            <p id="cf-email-error" className="mt-1.5 text-xs text-red-500 light:text-red-600">{errors.email}</p>
          ) : null}
        </div>
      </div>

      <div>
        <label htmlFor="cf-subject" className="mb-1.5 block text-sm font-medium text-foreground">
          Subject
        </label>
        <input id="cf-subject" name="subject" type="text" required placeholder="What's this about?" className={inputClass} aria-invalid={Boolean(errors.subject)} aria-describedby={errors.subject ? "cf-subject-error" : undefined} />
        {errors.subject ? (
          <p id="cf-subject-error" className="mt-1.5 text-xs text-red-500 light:text-red-600">{errors.subject}</p>
        ) : null}
      </div>

      <div>
        <label htmlFor="cf-message" className="mb-1.5 block text-sm font-medium text-foreground">
          Message
        </label>
        <textarea id="cf-message" name="message" rows={5} required placeholder="Tell me about the role or projectâ€¦" className={`${inputClass} resize-y`} aria-invalid={Boolean(errors.message)} aria-describedby={errors.message ? "cf-message-error" : undefined} />
        {errors.message ? (
          <p id="cf-message-error" className="mt-1.5 text-xs text-red-500 light:text-red-600">{errors.message}</p>
        ) : null}
      </div>

      <div className="sr-only" aria-hidden="true">
        <label htmlFor="cf-company-website">Company website</label>
        <input id="cf-company-website" name="company_website" type="text" tabIndex={-1} autoComplete="off" />
      </div>
      <input type="hidden" name="submitted_at" value={Date.now()} />

      <button
        type="submit"
        disabled={status === "sending"}
        className="inline-flex w-full items-center justify-center gap-2 rounded-lg bg-accent px-6 py-3 text-sm font-semibold text-white transition-colors hover:bg-accent-strong disabled:cursor-not-allowed disabled:opacity-60 sm:w-auto"
      >
        {status === "sending" ? (
          <>
            <Loader2 className="h-4 w-4 animate-spin" aria-hidden="true" />
            Preparingâ€¦
          </>
        ) : (
          <>
            <Send className="h-4 w-4" aria-hidden="true" />
            Send Message
          </>
        )}
      </button>

      {status === "success" ? (
        <p className="flex items-center gap-2 rounded-lg border border-accent/40 bg-accent/10 px-4 py-3 text-sm text-accent" role="status">
          <CheckCircle2 className="h-4 w-4 shrink-0" aria-hidden="true" />
          Your email draft has been prepared. If your mail app didn't open, write to me directly at {site.email}.
        </p>
      ) : null}

      {status === "error" ? (
        <p className="flex items-center gap-2 rounded-lg border border-red-500/40 bg-red-500/10 px-4 py-3 text-sm text-red-500 light:text-red-600" role="alert">
          <AlertCircle className="h-4 w-4 shrink-0" aria-hidden="true" />
          Please fix the highlighted fields and try again.
        </p>
      ) : null}
    </form>
  );
}