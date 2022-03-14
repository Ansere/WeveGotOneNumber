/*
Stolen code, all credits to the JLatexMath team
 */

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.scilab.forge.jlatexmath.DefaultTeXFont;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.scilab.forge.jlatexmath.cyrillic.CyrillicRegistration;
import org.scilab.forge.jlatexmath.greek.GreekRegistration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Convert {

    public static final int PDF = 0;
    public static final int PS = 1;
    public static final int EPS = 2;

    public static void toSVG(String latex, String file, boolean fontAsShapes) throws IOException, TranscoderException {
        DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(document);

        SVGGraphics2D g2 = new SVGGraphics2D(ctx, fontAsShapes);

        DefaultTeXFont.registerAlphabet(new CyrillicRegistration());
        DefaultTeXFont.registerAlphabet(new GreekRegistration());

        TeXFormula formula = new TeXFormula(latex);
        TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
        icon.setInsets(new Insets(5, 5, 5, 5));
        g2.setSVGCanvasSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        g2.setColor(Color.white);
        g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());

        JLabel jl = new JLabel();
        jl.setForeground(new Color(0, 0, 0));
        icon.paintIcon(jl, g2, 0, 0);

        boolean useCSS = true;
        FileOutputStream svgs = new FileOutputStream(file);
        Writer out = new OutputStreamWriter(svgs, "UTF-8");
        g2.stream(out, useCSS);
        svgs.flush();
        svgs.close();
        String svg_URI_input = new File("test.svg").toURI().toString();
        TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);
        OutputStream png_ostream = new FileOutputStream("test.png");
        TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);
        PNGTranscoder my_converter = new PNGTranscoder();
        my_converter.addTranscodingHint(PNGTranscoder.KEY_PIXEL_UNIT_TO_MILLIMETER,0.084672F);
        my_converter.addTranscodingHint(PNGTranscoder.KEY_BACKGROUND_COLOR, Color.WHITE);
        my_converter.transcode(input_svg_image, output_png_image);
        png_ostream.flush();
        png_ostream.close();
    }
}