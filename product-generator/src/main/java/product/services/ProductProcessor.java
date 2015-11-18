package product.services;

import core.service.IProcessor;
import core.service.impl.AbstractProcessor;
import core.utils.Utils;
import product.beans.Code;
import product.beans.Group;
import product.beans.Product;
import product.beans.ProductResult;

import java.util.ArrayList;
import java.util.List;

import static core.utils.Utils.DASH;
import static core.utils.Utils.NEW_LINE;
import static core.utils.Utils.SPACE;

/**
 * @author Mikhail Boldinov
 */
public class ProductProcessor extends AbstractProcessor<Product> implements IProcessor<ProductResult> {

    public ProductProcessor(Product bean) {
        super(bean);
    }

    @Override
    public ProductResult process() {
        ProductResult productResult = new ProductResult();
        productResult.setProducer(getProcessData().getProducer());
        productResult.setSerialNumber(getProcessData().getSerialNumber());
        productResult.setShortDescription(getProcessData().getShortDescription());
        productResult.setDescription(getProcessData().getDescription());
        productResult.setImageExtension(getProcessData().getImageExtension());
        productResult.setProductCodes(getProductCodes(getProcessData().getGroups()));
        return productResult;
    }

    private List<Code> getProductCodes(List<Group> groups) {
        List<Code> productCodes = new ArrayList<>();
        boolean first = true;
        while (hasNext(groups)) {
            if (!first) {
                next(groups);
            }

            Code productCode = getProductCode(groups);
            productCodes.add(productCode);
            first = false;
        }
        return productCodes;
    }

    private Code getProductCode(List<Group> groups) {
        String codeKey = "";
        String decoding = "";
        for (int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            Code code = group.getCodes().get(group.getPosition());
            codeKey += code.getKey();
            if (i < groups.size() - 1) {
                codeKey += group.getSeparator();
            }
            decoding += Utils.buildString(NEW_LINE, group.getName(), SPACE, DASH, SPACE, code.getValue());
        }
        return new Code(codeKey, decoding);
    }

    private boolean hasNext(List<Group> groups) {
        for (Group group : groups) {
            if (group.getPosition() < group.getCodes().size() - 1) {
                return true;
            }
        }
        return false;
    }

    private void next(List<Group> groups) {
        for (int i = groups.size() - 1; i >= 0; i--) {
            Group group = groups.get(i);
            if (group.incrementPosition()) {
                break;
            }
        }
    }
}
