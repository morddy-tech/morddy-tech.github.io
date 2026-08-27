import fs from "node:fs";
import path from "node:path";
import { site } from "@/data/site";

export function cvFileExists(): boolean {
  try {
    return fs.existsSync(path.join(process.cwd(), "public", site.cvPath.replace(/^\//, "")));
  } catch {
    return false;
  }
}