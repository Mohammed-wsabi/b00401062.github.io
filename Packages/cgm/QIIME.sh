#!/usr/bin/env bash

export PATH=~/Library/Conda/bin/:$PATH
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

## Importing Data

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $CGM/manifest.csv \
	--input-format PairedEndFastqManifestPhred33 \
	--output-path $CGM/demux.qza

qiime demux summarize \
	--i-data $CGM/demux.qza \
	--o-visualization $CGM/demux.qzv

## Denoising

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $CGM/demux.qza \
	--p-trim-left-f 0 \
	--p-trim-left-r 0 \
	--p-trunc-len-f 120 \
	--p-trunc-len-r 120 \
	--p-n-threads 0 \
	--o-table $CGM/table-raw.qza \
	--o-representative-sequences $CGM/rep-seqs-raw.qza \
	--o-denoising-stats $CGM/denoising.qza

qiime feature-table filter-features \
	--i-table $CGM/table-raw.qza \
	--p-min-frequency 2 \
	--o-filtered-table $CGM/table.qza

qiime feature-table filter-seqs \
	--i-data $CGM/rep-seqs-raw.qza \
	--i-table $CGM/table.qza \
	--o-filtered-data $CGM/rep-seqs.qza

qiime feature-table summarize \
	--i-table $CGM/table.qza \
	--m-sample-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/table.qzv

qiime feature-table tabulate-seqs \
	--i-data $CGM/rep-seqs.qza \
	--o-visualization $CGM/rep-seqs.qzv

qiime metadata tabulate \
	--m-input-file $CGM/denoising.qza \
	--o-visualization $CGM/denoising.qzv

## Phylogenetic Diversity Analyses

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $CGM/rep-seqs.qza \
	--o-alignment $CGM/aligned-rep-seqs.qza \
	--o-masked-alignment $CGM/masked-aligned-rep-seqs.qza \
	--o-tree $CGM/unrooted-tree.qza \
	--o-rooted-tree $CGM/rooted-tree.qza

qiime tools export \
	--input-path $CGM/unrooted-tree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/unrooted-tree.nwk

qiime tools export \
	--input-path $CGM/rooted-tree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/rooted-tree.nwk

## Diversity Analysis

### Alpha Rarefaction

qiime diversity alpha-rarefaction \
	--i-table $CGM/table.qza \
	--i-phylogeny $CGM/rooted-tree.qza \
	--p-max-depth 4778 \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/alpha-rarefaction.qzv

### Preprocessing

qiime diversity core-metrics-phylogenetic \
	--i-phylogeny $CGM/rooted-tree.qza \
	--i-table $CGM/table.qza \
	--p-sampling-depth 2035 \
	--m-metadata-file $CGM/metadata.tsv \
	--output-dir $CGM/core-metrics-results

### Alpha Diversity

qiime diversity alpha-group-significance \
	--i-alpha-diversity $CGM/core-metrics-results/faith_pd_vector.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/core-metrics-results/faith-pd-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity $CGM/core-metrics-results/evenness_vector.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/core-metrics-results/evenness-group-significance.qzv

### Beta Diversity

qiime diversity beta-group-significance \
	--i-distance-matrix $CGM/core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization $CGM/core-metrics-results/unweighted-unifrac-subject-significance.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix $CGM/core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--m-metadata-column Tissue \
	--o-visualization $CGM/core-metrics-results/unweighted-unifrac-tissue-significance.qzv \
	--p-pairwise

## Taxonomic Analysis

qiime feature-classifier classify-sklearn \
	--i-classifier $CGM/gg-13-8-99-515-806-nb-classifier.qza \
	--i-reads $CGM/rep-seqs.qza \
	--o-classification $CGM/taxonomy.qza

qiime metadata tabulate \
	--m-input-file $CGM/taxonomy.qza \
	--o-visualization $CGM/taxonomy.qzv

qiime taxa barplot \
	--i-table $CGM/table.qza \
	--i-taxonomy $CGM/taxonomy.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/taxa-bar-plots.qzv

## Differential Abundance Analysis With ANCOM

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

## Differential Abundance Analysis With Gneiss

qiime gneiss ilr-phylogenetic \
	--i-table $CGM/table.qza \
	--i-tree $CGM/rooted-tree.qza \
	--o-balances $CGM/balances.qza \
	--o-hierarchy $CGM/hierarchy.qza

qiime gneiss ols-regression \
	--p-formula "Tissue" \
	--i-table $CGM/balances.qza \
	--i-tree $CGM/hierarchy.qza \
	--m-metadata-file $CGM/metadata.tsv \
	--o-visualization $CGM/regression_summary.qzv

## Cleanup

source deactivate

unset CGM
unset PATH
