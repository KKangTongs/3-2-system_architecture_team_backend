#!/bin/bash

yaml_files=$(find . -type f -name "*.yaml")

for file in $yaml_files; do
    kubectl apply -f $file
done
