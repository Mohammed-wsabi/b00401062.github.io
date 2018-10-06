#!/usr/bin/env bash

qiime phylogeny align-to-tree-mafft-fasttree \
	--i-sequences $DATASETS/rep-seqs.qza \
	--o-alignment $DATASETS/aligned-rep-seqs.qza \
	--o-masked-alignment $DATASETS/masked-aligned-rep-seqs.qza \
	--o-tree $DATASETS/unrooted-tree.qza \
	--o-rooted-tree $DATASETS/rooted-tree.qza

qiime tools export \
	--input-path $DATASETS/unrooted-tree.qza \
	--output-path $DATASET

mv $DATASETS/tree.nwk $DATASETS/unrooted-tree.nwk

qiime tools export \
	--input-path $DATASETS/rooted-tree.qza \
	--output-path $DATASET

mv $DATASETS/tree.nwk $DATASETS/rooted-tree.nwk
