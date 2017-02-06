
package diser_5;
import java.io.*;
import org.apache.log4j.*;
//D:\Java\NetBeans 7.2.1\ide\modules\ext\log4j-1.2.15.jar

public class Diser_5 {
  static Logger log = Logger.getLogger(Diser_5.class); 
  final static int Num = 32;
  final static int mod = 256;
  
    public static void moveLeft(long[] array, int positions) {
        int size = array.length;
        for (int i = 0; i < positions; i++) 
        {
            long temp = array[0];
            for (int j = 1; j < size; j++) 
            {
                array[j-1] = array[j];
            }
            array[size-1] = temp;
        }
    }
    
    public static void chartPrint(long X[])
    {
        for(int i = 0; i < 256;  i++)
        {
            System.out.format("#%3d: %3d: ", i, X[i]);
            for(int d=0;d<=X[i];d++) {
                System.out.format("%s", "*");
            }
            System.out.print("\n");
        }
        System.out.print("\n");  
    }
    
    public static void cyclicCod(long X[])
    {
        String str = "", newBit = "0";
        long n = 13;
        for(int i = 0; i < 31; i++)
        {
            int bit = ((n&1)>0?1:0);
            str += Integer.toString(bit);
            if(bit == 1)
            {
                n = n >> 1;
                n =  n ^ 20;
            }
            else
            {
                n = n >> 1;                
            }
        }
        newBit = Integer.toString(Integer.parseInt(str.substring(0,1))^Integer.parseInt(str.substring(str.length()-1)));
        n = Long.parseLong(str + newBit, 2); 
        for(int i = 0; i < 256; i++)
        {

            X[i] = n;
            log.info(String.format("#" + i + "  " + str + newBit + " S[i] = " + X[i] + "%n"));    
            str = str.substring(str.length()-1) + str.substring(0, str.length()-1);
            newBit = Integer.toString(Integer.parseInt(str.substring(0,1))^Integer.parseInt(str.substring(str.length()-1)));
            n = Long.parseLong(str + newBit, 2);
        }
        log.info(String.format("%n"));
    }
    
    public static long[] doubleMul(long A[][])
    {
//----------------------------------------m1 m2 --------------------------------        
        long dil;
        int i, k, x1, l1 = 0, CF = 0;
        long[] mb1 = new long[Num];
        long[] mb2 = new long[Num];
        long[] ma1 = new long[Num];
        long[] ma2 = new long[Num];
        long[] c = new long[Num];
        long[] z = new long[Num];
        long[] s = new long[Num];
        for(i=Num-1; i>=0; i--)
        {
            k = 0;
            for(int j=Num-1; j>=0; j--)
            {
                dil = A[0][j] * A[1][i];
                mb1[k] = dil % mod;                
                mb2[k] = dil / mod;
                dil = A[1][j] * A[0][i];
                ma1[k] = dil % mod;                
                ma2[k] = dil / mod;
                k++;
            }
            System.out.print("\nmb1:   ");
            for(x1=0; x1<Num; x1++) System.out.print(mb1[x1]+" ");
            System.out.print("\n");
            System.out.print("mb2:   ");
            for(x1=0; x1<Num ;x1++) System.out.print(mb2[x1]+" ");
            System.out.print("\n");
            for(x1=0; x1<80 ;x1++) System.out.print(".");           
            System.out.print("\nma1:   ");
            for(x1=0; x1<Num; x1++) System.out.print(ma1[x1]+" ");
            System.out.print("\n");
            System.out.print("ma2:   ");
            for(x1=0; x1<Num ;x1++) System.out.print(ma2[x1]+" ");
            System.out.print("\n");
            for(x1=0; x1<80 ;x1++) System.out.print("-");
            System.out.print("\n");
//------------------------------------------- C --------------------------------            
            System.out.print("c:     ");
            CF = 0;
            for(x1=Num-1; x1>=0 ;x1--) 
            {
                c[x1] = mb1[x1] + mb2[x1] + ma1[x1] + ma2[x1] + CF;
                if(c[x1] > (3*mod-1)) CF = 3;
                else if(c[x1] > (2*mod-1)) CF = 2;
                else if(c[x1] > (mod-1)) CF = 1;
                else CF = 0;
                c[x1] = c[x1] % mod;
            }
            s[l1] += CF;
            for(x1=0; x1<Num ;x1++) System.out.print(c[x1]+" ");
            System.out.print("\n");
//------------------------------------------- Z --------------------------------            
            System.out.print("z:     ");
            CF = 0;
            for(x1=Num-1; x1>=0 ;x1--) 
            {
                z[x1] = z[x1] + c[x1] + CF;
                if(z[x1] > (3*mod-1)) CF = 3;
                else if(z[x1] > (2*mod-1)) CF = 2;
                else if(z[x1] > (mod-1)) CF = 1;
                else CF = 0;
                z[x1] = z[x1] % mod;
            }
            s[l1] += CF;
            for(x1=0; x1<Num ;x1++) System.out.print(z[x1]+" ");
            System.out.print("\n");
            for(x1=0; x1<80 ;x1++) System.out.print("*");
            System.out.print("\nCarry to s: " + s[l1] + "\n");
            l1++;
        }
        System.out.print("\ns:     ");
        for(x1=0; x1<Num ;x1++) System.out.print(s[x1]+" ");
        System.out.print("\n");
        for(x1=0; x1<80 ;x1++) System.out.print("=");
        System.out.print("\n");        
        System.out.print("\nRES:   ");
        CF = 0;
        for(x1=Num-1; x1>=0 ;x1--) 
        {
            s[x1] = s[x1] + z[x1] + CF;
            if(c[x1] > (3*mod-1)) CF = 3;
            else if(c[x1] > (2*mod-1)) CF = 2;
            else if(c[x1] > (mod-1)) CF = 1;
            else CF = 0;
            s[x1] = s[x1] % mod;
            }
        for(x1=0; x1<Num ;x1++) {
                    System.out.print(s[x1]+" ");
                }
        System.out.print("\n"); 
        return s;     
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
//========================================= LOG =========================================================          
        File f = new File("Diser_log.log");
        f.delete();
        PropertyConfigurator.configure("src/diser_5/newproperties.properties");
//=================================== READ FROM FILE ==== mas N =========================================         
        String inFile = "data.txt";
        FileInputStream fs = new FileInputStream(inFile);
        
        long N[] = new long[256];
        long S[] = new long[256];
        long pos = 1;                           //delete
        int buf;
        int ch;
        
        //log.info(String.format("%nПочаткове заповнення:%n"));
        cyclicCod(S);
        Integer mas[] = new Integer[256];
        log.info(String.format("%nВхідний файл:%n%n"));
        while((buf = fs.read())!=-1){
            ch = buf;
            mas[(int)pos-1] = buf;
            log.info((char)ch);
            N[ch]++;
            S[ch] += N[ch] * pos;   //summa dobutkiv: kilkist*posyciu
            //log.info(String.format("%n%nS_old[%c]:%d%n",ch, S[ch])); 
            S[ch]=(S[ch]%4294967296L)+(S[ch]/4294967296L);  //pryvedennya do 32 bit
            //log.info(String.format("S_new[%c]:%d%n",ch, S[ch])); 
            pos++;                              
        }

        //////////////////////////////////////////////////////////////
        int length = (int)pos - 1;
        log.info("\n");
        String str = "";
        for(int i = 0; i < length;  i++)
        {
            log.info(mas[i] + " ");
            if (mas[i] != null)
            str += (Integer.toHexString(mas[i]));
        }

        log.info(String.format("%nHASH hex:%n%s%n", str));
        //////////////////////////////////////////////////////////////

        log.info(String.format("%n%nКількості символів: %d %n", length));
        for(int i = 0; i < 256;  i++)
        {
            //if(N[i]!=0) 
                log.info(String.format("#%3d: N[%c]-> %10d ->   S[]-> %3d  %n", i, (char)i, N[i], S[i] ));
            if( (i+1)%8 == 0 ) {
                log.info(String.format("%n"));
            }
        }    
        //chartPrint(N);

//=================================== BUILD ==== mas H and Ht ===========================================         
        long Ht[][] = new long[32][32];
        long Hs[][] = new long[32][32];        
        long H[] = new long[32];  
        long q32 = 2147483648L; 
        int i, j, p = 0; 
        long x;
        long startByte = 0;
        long SumCarry[][] = new long[3][32];
        
        for(int k = 0; k < 32; k++)
        {
            //log.info(String.format("%n"));  
            for(int l = 0; l < 32; l++)
            {
                int q8 = 128, sum = 0;
                for(i = p; i < p+8;  i++)
                {
                    x = S[i] & q32 ;
                    sum += (((x>0)?1:0) * q8);
                    q8 /= 2; 
                }
                p = i;
                Ht[k][l] = sum;
                SumCarry[0][k] += sum;
                //log.info(String.format("#%2d: %3d -> %8s%n",l, Ht[k][l], Long.toBinaryString(Ht[k][l])));
            } 
            for(int m=0;m<32;m++) Hs[k][m] = (SumCarry[0][k]/mod + SumCarry[0][k]%mod);            
            q32 /= 2;
            p = 0;
        }
        
        log.info(String.format("%n%nMAS_M %n" ));
        for( i=0;i<154;i++) log.info(String.format("-"));
        log.info(String.format("%n"));
        for( i=0;i<32;i++)
        {
            log.info(String.format("#%2d: ",i)); 
            for( j=0;j<32;j++)
            {
                log.info(String.format("%3d ",Ht[i][j])); 
            }
            log.info(String.format("   %3d%n",SumCarry[0][i])); 
        }     

        log.info(String.format("%n%nMAS_S  %n" ));
        for( i=0;i<138;i++) log.info(String.format("-"));
        log.info(String.format("%n"));
        for( i=0;i<32;i++)
        {
            log.info(String.format("#%2d: ",i)); 
            for( j=0;j<32;j++)
            {
                log.info(String.format("%3d ",Hs[i][j])); 
            }
            log.info(String.format("%n")); 
        }

        log.info(String.format("%n%nSUMA: %n" ));
        for( i=0;i<138;i++) log.info(String.format("-"));
        log.info(String.format("%n"));
        for( i=0;i<32;i++)
        {
            log.info(String.format("#%2d: ",i)); 
            for( j=0;j<32;j++)
            {
                Hs[i][j] += Ht[i][j];
                Hs[i][j] = Hs[i][j]/mod + Hs[i][j]%mod;
                log.info(String.format("%3d ", Hs[i][j])); 
            }
            log.info(String.format("%n")); 
        }  

        for( i=0;i<138;i++) log.info(String.format("-"));
        log.info(String.format("%nRES: "));
        for( i=0;i<32;i++)
        {
            for( j=0;j<32;j++)
            {
                SumCarry[1][i] += Hs[j][i];
                SumCarry[1][i] = SumCarry[1][i]/mod + SumCarry[1][i]%mod;
            }
        log.info(String.format("%3d ", SumCarry[1][i]));             
        }
        
//=================================== OUTPUT RESULT Hash bin/hex ========================================           
        log.info(String.format("%n%n" ));
        str = "";
        for(j=0;j<32;j++) {
            str += (Long.toBinaryString(SumCarry[1][j]) + " ");
        }   
        log.info(String.format("HASH binary:%n%n%s%n", str));
        str = "";
        for(j=0;j<32;j++) {
            str += (Long.toHexString(SumCarry[1][j]) + " ");
        }
        log.info(String.format("%nHASH hex:%n%n%s%n", str));
    }
}
