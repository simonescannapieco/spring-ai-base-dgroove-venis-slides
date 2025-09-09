# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a LaTeX Beamer slide deck project for a Spring AI course targeting Venis S.p.A employees. The course covers generative AI with Java/Spring Boot, delivered in Italian.

## Build Commands

**Compile slides from any module directory:**
```bash
./compile_slides.sh
```

**Manual compilation (XeLaTeX required):**
```bash
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex # Run twice
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout.tex  # Run twice
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout_notes.tex  # Run twice
```

**Clean build artifacts:**
```bash
rm -rf out/main*
```

**CRITICAL:** Always use XeLaTeX, never pdflatex. Compilation must run twice for proper references.

## Architecture

### Module Structure
- **Theory modules**: `/theory/0_intro/` through `/theory/7_rag/`
- **Practice modules**: `/practice/1_dev_env/` and `/practice/2_spring_intro/`

Each module contains:
- `main.tex` - Standard presentation
- `main_handout.tex` - Handout version
- `main_handout_notes.tex` - Handout with speaker notes
- `[module].tex` - Content file
- `compile_slides.sh` - Build script
- `img/` - Module images
- `out/` - Generated PDFs

### Dependency Chain
```
main.tex → initialize_beamer.tex → base.tex → initialize_document.tex → [content].tex
```
```
main_handout.tex → initialize_beamer_handout.tex → base.tex → initialize_document.tex → [content].tex
```
```
main.tex → initialize_beamer_handout.tex → base.tex → base_notes.tex → initialize_document.tex → [content].tex
```

### Core Configuration
- `/base.tex` - Theme, colors, commands, Venis branding
- `/img/base/` - Shared logos and graphics
- Moloch theme with Venis corporate colors (blue/yellow)

## Content Guidelines

### Language & Formatting
- All content in Italian
- Progressive reveals: `\onslide<2->\item[\alert{\faHandORight}]`
- Consistent minipage widths: `0.6\textwidth`
- FontAwesome icons for bullets and branding

### Code Highlighting
- Java/Spring Boot examples using minted package
- Solarized light theme for code blocks
- Shell sessions with Monokai theme
- Epigraph formatting for code snippets

### Build Outputs
- `main.pdf` - Animated presentation
- `main_handout.pdf` - Static handout
- `main_handout_notes.pdf` - Handout with speaker notes

## Technical Requirements

- **LaTeX Engine**: XeLaTeX (for Roboto font support)
- **Packages**: Beamer, minted, fontawesome, tikz
- **Theme**: Moloch (evolution of Metropolis)
- **Font**: Roboto sfdefault
- **Graphics**: SVG via Inkscape, standard formats