#!/usr/bin/env bash

export PATH="~/Library/Conda/bin/:$PATH"
export CGM=~/Downloads/Researches/CGM/Datasets

source activate qiime

## Importing Data

qiime tools import \
	--type "SampleData[PairedEndSequencesWithQuality]" \
	--input-path $CGM/Manifest.csv \
	--input-format PairedEndFastqManifestPhred33
	--output-path $CGM/Demultiplexed.qza \

qiime demux summarize \
	--i-data $CGM/Demultiplexed.qza \
	--o-visualization $CGM/Demultiplexed.qzv

## Denoising

qiime dada2 denoise-paired \
	--i-demultiplexed-seqs $CGM/Demultiplexed.qza \
	--p-trim-left-f 0 \
	--p-trim-left-r 0 \
	--p-trunc-len-f 200 \
	--p-trunc-len-r 200 \
	--p-n-threads 0 \
	--o-representative-sequences $CGM/Representative.qza \
	--o-table $CGM/FeatureTable.qza \
	--o-denoising-stats $CGM/DenoisingStats.qza

qiime feature-table tabulate-seqs \
	--i-data $CGM/Representative.qza \
	--o-visualization $CGM/Representative.qzv

qiime feature-table summarize \
	--i-table $CGM/FeatureTable.qza \
	--o-visualization $CGM/FeatureTable.qzv \

qiime metadata tabulate \
	--m-input-file $CGM/DenoisingStats.qza \
	--o-visualization $CGM/DenoisingStats.qzv

## Phylogenetic Diversity Analyses

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $CGM/Representative.qza \
	--o-alignment $CGM/Alignement.qza \
	--o-masked-alignment $CGM/MaskedAlignment.qza \
	--o-tree $CGM/UnrootedTree.qza \
	--o-rooted-tree $CGM/RootedTree.qza

qiime tools export \
	--input-path $CGM/UnrootedTree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/UnrootedTree.nwk

qiime tools export \
	--input-path $CGM/RootedTree.qza \
	--output-path $CGM

mv $CGM/tree.nwk $CGM/RootedTree.nwk

## Alpha and Beta Diversity Analysis

qiime diversity core-metrics-phylogenetic \
	--i-phylogeny rooted-tree.qza \
	--i-table table.qza \
	--p-sampling-depth 1109 \
	--m-metadata-file sample-metadata.tsv \
	--output-dir core-metrics-results

qiime diversity alpha-group-significance \
	--i-alpha-diversity core-metrics-results/faith_pd_vector.qza \
	--m-metadata-file sample-metadata.tsv \
	--o-visualization core-metrics-results/faith-pd-group-significance.qzv

qiime diversity alpha-group-significance \
	--i-alpha-diversity core-metrics-results/evenness_vector.qza \
	--m-metadata-file sample-metadata.tsv \
	--o-visualization core-metrics-results/evenness-group-significance.qzv

qiime diversity beta-group-significance \
	--i-distance-matrix core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file sample-metadata.tsv \
	--m-metadata-column BodySite \
	--o-visualization core-metrics-results/unweighted-unifrac-body-site-significance.qzv \
	--p-pairwise

qiime diversity beta-group-significance \
	--i-distance-matrix core-metrics-results/unweighted_unifrac_distance_matrix.qza \
	--m-metadata-file sample-metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization core-metrics-results/unweighted-unifrac-subject-group-significance.qzv \
	--p-pairwise

qiime emperor plot \
	--i-pcoa core-metrics-results/unweighted_unifrac_pcoa_results.qza \
	--m-metadata-file sample-metadata.tsv \
	--p-custom-axes DaysSinceExperimentStart \
	--o-visualization core-metrics-results/unweighted-unifrac-emperor-DaysSinceExperimentStart.qzv

qiime emperor plot \
	--i-pcoa core-metrics-results/bray_curtis_pcoa_results.qza \
	--m-metadata-file sample-metadata.tsv \
	--p-custom-axes DaysSinceExperimentStart \
	--o-visualization core-metrics-results/bray-curtis-emperor-DaysSinceExperimentStart.qzv

## Alpha Rarefaction Plotting

qiime diversity alpha-rarefaction \
	--i-table table.qza \
	--i-phylogeny rooted-tree.qza \
	--p-max-depth 4000 \
	--m-metadata-file sample-metadata.tsv \
	--o-visualization alpha-rarefaction.qzv

## Taxonomic Analysis

qiime feature-classifier classify-sklearn \
	--i-classifier gg-13-8-99-515-806-nb-classifier.qza \
	--i-reads rep-seqs.qza \
	--o-classification taxonomy.qza

qiime metadata tabulate \
	--m-input-file taxonomy.qza \
	--o-visualization taxonomy.qzv

qiime taxa barplot \
	--i-table table.qza \
	--i-taxonomy taxonomy.qza \
	--m-metadata-file sample-metadata.tsv \
	--o-visualization taxa-bar-plots.qzv

## Differential Abundance Testing

qiime feature-table filter-samples \
	--i-table table.qza \
	--m-metadata-file sample-metadata.tsv \
	--p-where "BodySite='gut'" \
	--o-filtered-table gut-table.qza

qiime composition add-pseudocount \
	--i-table gut-table.qza \
	--o-composition-table comp-gut-table.qza

qiime composition ancom \
	--i-table comp-gut-table.qza \
	--m-metadata-file sample-metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization ancom-Subject.qzv

qiime taxa collapse \
	--i-table gut-table.qza \
	--i-taxonomy taxonomy.qza \
	--p-level 6 \
	--o-collapsed-table gut-table-l6.qza

qiime composition add-pseudocount \
	--i-table gut-table-l6.qza \
	--o-composition-table comp-gut-table-l6.qza

qiime composition ancom \
	--i-table comp-gut-table-l6.qza \
	--m-metadata-file sample-metadata.tsv \
	--m-metadata-column Subject \
	--o-visualization l6-ancom-Subject.qzv

## Cleanup

source deactivate

unset CGM
unset PATH
