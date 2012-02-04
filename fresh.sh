cd bhave
play clean
play deps --sync
play ec
play build-module
cd ../bhave-test
play clean
play deps --sync
play ec
