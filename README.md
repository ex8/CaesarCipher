# CaesarCipher
Extremely simple encryption algorithm. Also known as the Shift Cipher. 

# How it works
A Caesar Cipher encryption "shifts" each individual character of a string forward by a certain number or "key". Each letter in the alphabet is shifted to the letter in the alphabet that is "key" places past the original letter.

Quick Shifting Example: 
```
s = "IEATPIZZA";
String c = caesarify(s, 1);
c = JFBUQJAAB // Output
```
If the character is A or Z, it will wrap around the alphabet accordingly.


The encryption algorithmn goes through a series of string manipulations before it runs caesarify(). However all these methods are collected into their respective methods; encrypString(String s, int shift, int group) and decryptString(String s, int shift).

Encrypting a string using the entire functionality manually: 
normalizeText(String s) -> obify(String s) -> caesarify(String s, int shift) -> groupify(String s, int n)

Ungroupify, Un-caesarify, Unobify
Decrypting a string using the entire functionality manually (you must know shift key value used in caesarify when encrypting):
ungroupify(String s) -> caesarify(String s, int shift) -> unobify(String s)

# Credits 
This project was taken from Microsoft MOOC
