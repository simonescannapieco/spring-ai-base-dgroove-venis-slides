#!/bin/bash

rm -rf out/*

xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex

xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout.tex

xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout_notes.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout_notes.tex

mv out/main.pdf out/${PWD##*/}.pdf
mv out/main_handout.pdf out/${PWD##*/}_handout.pdf
mv out/main_handout_notes.pdf out/${PWD##*/}_handout_notes.pdf