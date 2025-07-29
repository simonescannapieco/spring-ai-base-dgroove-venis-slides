#!/bin/bash
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex
xelatex -shell-escape -synctex=1 -interaction=nonstopmode -output-directory=out main.tex