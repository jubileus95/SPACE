package com.parma.filter;

import static org.junit.Assert.*;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import com.parma.images.ImageHandler;

public class DnlmFilterTest {

  @Test
  public void test() {
    
    ImageHandler ih = new ImageHandler();
    Mat imagen = ih.leerImagenGrises("test_files/input/lena.jpg");
    
    DnlmFilter filter = new DnlmFilter();
    
    Mat res = filter.filter(imagen, 3, 3, 5);
    
<<<<<<< HEAD
    ih.guardarImagen("C:/Users/Joel/Desktop/TEMP", "celula", "jpg", res);
=======
    ih.guardarImagen("C:/Users/Eliot/Desktop", "2", "png", res);
>>>>>>> fa3dc1ebf9eb7cb0a81bcfa57e4a044f74599b97
    
    System.out.println("Checksum: " + Core.sumElems(res).val[0]);        
    
  }

}
