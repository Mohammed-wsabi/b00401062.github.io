#!/usr/bin/env bash

qiime composition add-pseudocount \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--o-composition-table $DATASETS/FeatureTable[Composition]/comp-table.qza

qiime composition ancom \
	--i-table $DATASETS/FeatureTable[Composition]/comp-table.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/composition-ancom/ancom-tissue.qzv

qiime taxa collapse \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--i-taxonomy $DATASETS/FeatureData[Taxonomy]/taxonomy.qza \
	--p-level 6 \
	--o-collapsed-table $DATASETS/FeatureTable[Frequency]/table-l6.qza

qiime composition add-pseudocount \
	--i-table $DATASETS/FeatureTable[Frequency]/table-l6.qza \
	--o-composition-table $DATASETS/FeatureTable[Composition]/comp-table-l6.qza

qiime composition ancom \
	--i-table $DATASETS/FeatureTable[Composition]/comp-table-l6.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $DATASETS/composition-ancom/ancom-tissue-l6.qzv
