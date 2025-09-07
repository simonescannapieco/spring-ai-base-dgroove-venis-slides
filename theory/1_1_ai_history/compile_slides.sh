#!/bin/bash

rm -rf out/main*

xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex

xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout.tex

xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout_notes.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main_handout_notes.tex