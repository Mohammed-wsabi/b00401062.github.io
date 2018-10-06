#!/usr/bin/env bash

qiime composition add-pseudocount \
	--i-table $DATASETS/table.qza \
	--o-composition-table $DATASETS/comp-table.qza

qiime composition ancom \
	--i-table $DATASETS/comp-table.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/ancom-tissue.qzv

qiime taxa collapse \
	--i-table $DATASETS/table.qza \
	--i-taxonomy $DATASETS/taxonomy.qza \
	--p-level 6 \
	--o-collapsed-table $DATASETS/table-l6.qza

qiime composition add-pseudocount \
	--i-table $DATASETS/table-l6.qza \
	--o-composition-table $DATASETS/comp-table-l6.qza

qiime composition ancom \
	--i-table $DATASETS/comp-table-l6.qza \
	--m-metadata-file $DATASETS/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/ancom-tissue-l6.qzv
