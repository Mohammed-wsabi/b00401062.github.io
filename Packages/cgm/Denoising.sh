#!/usr/bin/env bash

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $DATASETS/SampleData[PairedEndSequencesWithQuality]/demux.qza \
	--p-trunc-len-f 0 \
	--p-trunc-len-r 0 \
	--p-trunc-q 20 \
	--p-n-threads 0 \
	--o-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--o-representative-sequences $DATASETS/FeatureData[Sequence]/rep-seqs.qza \
	--o-denoising-stats $DATASETS/SampleData[DADA2Stats]/stats-dada2.qza

qiime feature-table summarize \
	--i-table $DATASETS/FeatureTable[Frequency]/table.qza \
	--m-sample-metadata-file $DATASETS/Metadata/metadata.tsv \
	--o-visualization $DATASETS/FeatureTable[Frequency]/table.qzv

qiime feature-table tabulate-seqs \
	--i-data $DATASETS/FeatureData[Sequence]/rep-seqs.qza \
	--o-visualization $DATASETS/FeatureData[Sequence]/rep-seqs.qzv

qiime metadata tabulate \
	--m-input-file $DATASETS/SampleData[DADA2Stats]/stats-dada2.qza \
	--o-visualization $DATASETS/SampleData[DADA2Stats]/stats-dada2.qzv
