#!/usr/bin/env bash

qiime tools import \
	--type "FeatureData[Sequence]" \
	--input-path $DATASETS/otu/_otus.fasta \
	--output-path $DATASETS/otu/_otus.qza

qiime tools import \
	--type "FeatureData[Taxonomy]" \
	--input-format HeaderlessTSVTaxonomyFormat \
	--input-path $DATASETS/otu/_otu_taxonomy.txt \
	--output-path $DATASETS/otu/_otu_taxonomy.qza

qiime feature-classifier extract-reads \
	--i-sequences $DATASETS/otu/_otus.qza \
	--p-f-primer CCTACGGGNGGCWGCAG \
	--p-r-primer GACTACHVGGGTATCTAATCC \
	--o-reads $DATASETS/otu/_otus_ref_seqs.qza

qiime feature-classifier fit-classifier-naive-bayes \
	--i-reference-reads $DATASETS/otu/_otus_ref_seqs.qza \
	--i-reference-taxonomy $DATASETS/otu/_otu_taxonomy.qza \
	--o-classifier $DATASETS/gg-13-8-99-nb-classifier.qza
