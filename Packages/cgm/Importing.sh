#!/usr/bin/env bash

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $DATASETS/Manifest/manifest.csv \
	--input-format PairedEndFastqManifestPhred33 \
	--output-path $DATASETS/SampleData[PairedEndSequencesWithQuality]/demux.qza

qiime demux summarize \
	--i-data $DATASETS/SampleData[PairedEndSequencesWithQuality]/demux.qza \
	--o-visualization $DATASETS/SampleData[PairedEndSequencesWithQuality]/demux.qzv
