revcomp = function(DNAstr) {
  step1 = chartr("acgt","tgca",DNAstr)
  step2 = unlist(strsplit(step1, split=""))
  step3 = rev(step2)
  step4 = paste(step3, collapse="")
  return(step4)
}

chr1_acceptor_site_seqs = read.table("/Users/gulsah/chr1/chr1_acceptor_site_sequences.txt");
chr1_acceptor_sites_m = as.matrix(chr1_acceptor_site_seqs, ncol=4);

for(i in 1:length(chr1_acceptor_sites_m[,1])){
  if(chr1_acceptor_sites_m[i,3] == "-1") {
    chr1_acceptor_sites_m[i,4] = revcomp(chr1_acceptor_sites_m[i,4]);
  }
}

write.csv(chr1_acceptor_sites_m, "/Users/gulsah/chr1/chr1_acceptor_site_sequences_rev_comp.csv")