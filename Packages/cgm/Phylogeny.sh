#!/usr/bin/env bash

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $DATASETS/FeatureData[Sequence]/rep-seqs.qza \
	--o-alignment $DATASETS/FeatureData[AlignedSequence]/aligned-rep-seqs.qza \
	--o-masked-alignment $DATASETS/FeatureData[AlignedSequence]/masked-aligned-rep-seqs.qza \
	--o-tree $DATASETS/Phylogeny/unrooted-tree.qza \
	--o-rooted-tree $DATASETS/Phylogeny/rooted-tree.qza

qiime tools export \
	--input-path $DATASETS/Phylogeny[Unrooted]/unrooted-tree.qza \
	--output-path $DATASETS/Phylogeny[Unrooted]

mv $DATASETS/Phylogeny[Unrooted]/tree.nwk $DATASETS/Phylogeny[Unrooted]/unrooted-tree.nwk

qiime tools export \
	--input-path $DATASETS/Phylogeny[Rooted]/rooted-tree.qza \
	--output-path $DATASETS/Phylogeny[Rooted]

mv $DATASETS/Phylogeny[Rooted]/tree.nwk $DATASETS/Phylogeny[Rooted]/rooted-tree.nwk
