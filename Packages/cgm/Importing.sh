#!/usr/bin/env bash

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $DATASETS/manifest.csv \
	--input-format PairedEndFastqManifestPhred33 \
	--output-path $DATASETS/demux.qza

qiime demux summarize \
	--i-data $DATASETS/demux.qza \
	--o-visualization $DATASETS/demux.qzv
