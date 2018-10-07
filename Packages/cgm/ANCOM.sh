#!/usr/bin/env bash

qiime composition add-pseudocount \
	--i-table $DATASETS/dada2-denoise-paired/table.qza \
	--o-composition-table $DATASETS/composition-add-pseudocount/comp-table.qza

qiime composition ancom \
	--i-table $DATASETS/composition-add-pseudocount/comp-table.qza \
	--m-metadata-file $DATASETS/metadata/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/visualization/composition-ancom-tissue.qzv

qiime taxa collapse \
	--i-table $DATASETS/dada2-denoise-paired/table.qza \
	--i-taxonomy $DATASETS/feature-classifier-classify-sklearn/taxonomy.qza \
	--p-level 6 \
	--o-collapsed-table $DATASETS/taxa-collapse/table-l6.qza

qiime composition add-pseudocount \
	--i-table $DATASETS/taxa-collapse/table-l6.qza \
	--o-composition-table $DATASETS/composition-add-pseudocount/comp-table-l6.qza

qiime composition ancom \
	--i-table $DATASETS/composition-add-pseudocount/comp-table-l6.qza \
	--m-metadata-file $DATASETS/metadata/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/visualization/composition-ancom-tissue-l6.qzv
