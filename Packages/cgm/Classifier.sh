#!/usr/bin/env bash

qiime tools import \
	--type "FeatureData[Sequence]" \
	--input-path $DATASETS/99_otus.fasta \
	--output-path $DATASETS/99_otus.qza

qiime tools import \
	--type "FeatureData[Taxonomy]" \
	--input-format HeaderlessTSVTaxonomyFormat \
	--input-path $DATASETS/99_otu_taxonomy.txt \
	--output-path $DATASETS/99_otu_taxonomy.qza

qiime feature-classifier extract-reads \
	--i-sequences $DATASETS/99_otus.qza \
	--p-f-primer CCTACGGGNGGCWGCAG \
	--p-r-primer GACTACHVGGGTATCTAATCC \
	--o-reads $DATASETS/99_otus_ref_seqs.qza

qiime feature-classifier fit-classifier-naive-bayes \
	--i-reference-reads $DATASETS/99_otus_ref_seqs.qza \
	--i-reference-taxonomy $DATASETS/99_otu_taxonomy.qza \
	--o-classifier $DATASETS/gg-13-8-99-nb-classifier.qza
