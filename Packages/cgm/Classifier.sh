#!/usr/bin/env bash

qiime tools import \
	--type 'FeatureData[Sequence]' \
	--input-path $CGM/85_otus.fasta \
	--output-path $CGM/85_otus.qza

qiime tools import \
	--type 'FeatureData[Taxonomy]' \
	--input-format HeaderlessTSVTaxonomyFormat \
	--input-path $CGM/85_otu_taxonomy.txt \
	--output-path $CGM/ref-taxonomy.qza

qiime feature-classifier extract-reads \
	--i-sequences $CGM/85_otus.qza \
	--p-f-primer CCTACGGGNGGCWGCAG \
	--p-r-primer GACTACHVGGGTATCTAATCC \
	--p-trunc-len 120 \
	--o-reads $CGM/ref-seqs.qza

qiime feature-classifier fit-classifier-naive-bayes \
	--i-reference-reads $CGM/ref-seqs.qza \
	--i-reference-taxonomy $CGM/ref-taxonomy.qza \
	--o-classifier $CGM/classifier.qza
