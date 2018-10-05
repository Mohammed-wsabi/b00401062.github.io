#!/usr/bin/env bash

qiime composition add-pseudocount \
	--i-table $CGM/table.qza \
	--o-composition-table $CGM/comp-table.qza

qiime composition ancom \
	--i-table $CGM/comp-table.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $CGM/ancom-tissue.qzv

qiime taxa collapse \
	--i-table $CGM/table.qza \
	--i-taxonomy $CGM/taxonomy.qza \
	--p-level 6 \
	--o-collapsed-table $CGM/table-l6.qza

qiime composition add-pseudocount \
	--i-table $CGM/table-l6.qza \
	--o-composition-table $CGM/comp-table-l6.qza

qiime composition ancom \
	--i-table $CGM/comp-table-l6.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $CGM/ancom-tissue-l6.qzv
