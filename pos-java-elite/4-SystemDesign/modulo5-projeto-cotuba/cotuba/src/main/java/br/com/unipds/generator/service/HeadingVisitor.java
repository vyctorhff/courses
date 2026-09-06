package br.com.unipds.generator.service;

import org.commonmark.node.AbstractVisitor;
import org.commonmark.node.Heading;
import org.commonmark.node.Text;

public class HeadingVisitor extends AbstractVisitor {

    @Override
    public void visit(Heading heading) {
        if (heading.getLevel() == 1) {
            // capítulo
            String chapterTitle = ((Text) heading.getFirstChild()).getLiteral();
            // TODO: usar título do capítulo
        } else if (heading.getLevel() == 2) {
            // seção
            String section = ((Text) heading.getFirstChild()).getLiteral();
        } else if (heading.getLevel() == 3) {
            // título
            String title = ((Text) heading.getFirstChild()).getLiteral();
        }
    }
}
