




public class Stemer {
      int stem(String st) {
// 5
char buffer[]=st.toCharArray();
int len=st.length();		  
if ((len > 6) && (st.endsWith("ाएंगी")
|| st.endsWith("ाएंगे")
|| st.endsWith("ाऊंगी")
|| st.endsWith("ाऊंगा")
|| st.endsWith("ाइयाँ")
|| st.endsWith("ाइयों")
|| st.endsWith("ाइयां")
))

return len - 5;
// 4
if ((len > 5) && (st.endsWith("ाएगी")
||st.endsWith("ाएगा")
|| st.endsWith("ाओगी")
|| st.endsWith("ाओगे")
||st.endsWith("एंगी")
|| st.endsWith("ेंगी")
||st.endsWith("एंगे")
||st.endsWith("ेंगे")
||st.endsWith("ूंगी")
||st.endsWith("ूंगा")
||st.endsWith("ातीं")
||st.endsWith("नाओं")
||st.endsWith("नाएं")
||st.endsWith("ताओं")
||st.endsWith("ताएं")
||st.endsWith("ियाँ")
||st.endsWith("ियों")
|| st.endsWith("ियां")
))
return len - 4;
// 3
if ((len > 4) && (st.endsWith("ाकर")
|| st.endsWith("ाइए")
|| st.endsWith("ाईं")
|| st.endsWith("ाया")
|| st.endsWith("ेगी")
 ||st.endsWith("ोगी")
|| st.endsWith("ोगे")
|| st.endsWith("ाने")
||st.endsWith("ाना")
||st.endsWith("ाते")
||st.endsWith("ाती")
||st.endsWith("ाता")
||st.endsWith("तीं")
||st.endsWith("ाओं")
||st.endsWith("ाएं")
||st.endsWith("ुओं")
||st.endsWith("ुएं")
||st.endsWith("ुआं")
))
return len - 3;
// 2
if ((len > 3) && (st.endsWith("कर")
|| st.endsWith("ाओ")
|| st.endsWith("िए")
|| st.endsWith("ाई")
|| st.endsWith("ाए")
|| st.endsWith("ने")
|| st.endsWith("नी")
|| st.endsWith("ना")
|| st.endsWith("ते")
|| st.endsWith("ीं")
|| st.endsWith("ती")
|| st.endsWith("ता")
|| st.endsWith("ाँ")
|| st.endsWith("ां")
|| st.endsWith("ों")
|| st.endsWith("ें")
))
return len - 2;
// 1
if ((len > 2) && (st.endsWith("ो")
|| st.endsWith("े")
|| st.endsWith("ू")
||st.endsWith("ु")
|| st.endsWith("ी")
||st.endsWith("ि")
||st.endsWith("ा")
))
return len - 1;
return len;
}
}