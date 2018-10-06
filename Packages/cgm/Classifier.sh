#!/usr/bin/env bash

qiime tools import \
	--type "FeatureData[Sequence]" \
	--input-path $CGM/99_otus.fasta \
	--output-path $CGM/99_otus.qza

qiime tools import \
	--type "FeatureData[Taxonomy]" \
	--input-format HeaderlessTSVTaxonomyFormat \
	--input-path $CGM/99_otu_taxonomy.txt \
	--output-path $CGM/99_otu_taxonomy.qza

qiime feature-classifier extract-reads \
	--i-sequences $CGM/99_otus.qza \
	--p-f-primer CCTACGGGNGGCWGCAG \
	--p-r-primer GACTACHVGGGTATCTAATCC \
	--o-reads $CGM/99_otus_ref_seqs.qza

qiime feature-classifier fit-classifier-naive-bayes \
	--i-reference-reads $CGM/99_otus_ref_seqs.qza \
	--i-reference-taxonomy $CGM/99_otu_taxonomy.qza \
	--o-classifier $CGM/gg-13-8-99-nb-classifier.qza
