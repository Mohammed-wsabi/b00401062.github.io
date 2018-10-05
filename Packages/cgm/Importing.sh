#!/usr/bin/env bash

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $CGM/manifest.csv \
	--input-format PairedEndFastqManifestPhred33 \
	--output-path $CGM/demux.qza

qiime demux summarize \
	--i-data $CGM/demux.qza \
	--o-visualization $CGM/demux.qzv
