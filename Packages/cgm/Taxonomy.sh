#!/usr/bin/env bash

qiime feature-classifier classify-sklearn \
	--i-classifier $DATASETS/TaxonomicClassifier/gg-13-8-99-nb-classifier.qza \
	--i-reads $DATASETS/FeatureData[Sequence]/rep-seqs.qza \
	--o-classification $DATASETS/FeatureData[Taxonomy]/taxonomy.qza

qiime metadata tabulate \
	--m-input-file $DATASETS/FeatureData[Taxonomy]/taxonomy.qza \
	--o-visualization $DATASETS/FeatureData[Taxonomy]/taxonomy.qzv

qiime taxa barplot \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--i-taxonomy $DATASETS/FeatureData[Taxonomy]/taxonomy.qza \
	--m-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/taxa-barplot/taxa-barplots.qzv
