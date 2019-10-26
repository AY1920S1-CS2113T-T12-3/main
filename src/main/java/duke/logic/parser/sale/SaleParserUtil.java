package duke.logic.parser.sale;

import duke.logic.command.sale.SaleDescriptor;
import duke.logic.parser.commons.ArgumentMultimap;
import duke.logic.parser.commons.TimeParser;

import static duke.logic.parser.commons.CliSyntax.*;

class SaleParserUtil {

    static SaleDescriptor createDescriptor(ArgumentMultimap map) {
        SaleDescriptor descriptor = new SaleDescriptor();

        if (map.getValue(PREFIX_SALE_DESCRIPTION).isPresent()) {
            descriptor.setDescription(map.getValue(PREFIX_SALE_DESCRIPTION).get());
        }
        if (map.getValue(PREFIX_SALE_VALUE).isPresent()) {
            descriptor.setValue(Double.parseDouble(map.getValue(PREFIX_SALE_VALUE).get()));
        }
        if (map.getValue(PREFIX_SALE_IS_SPEND).isPresent()) {
            descriptor.setSpend(Boolean.parseBoolean(map.getValue(PREFIX_SALE_IS_SPEND).get()));
        }
        if (map.getValue(PREFIX_SALE_DATE).isPresent()) {
            descriptor.setSaleDate(TimeParser.convertStringToDate(
                    map.getValue(PREFIX_SALE_DATE).get()));
        }
        if (map.getValue(PREFIX_SALE_REMARKS).isPresent()) {
            descriptor.setRemarks(map.getValue(PREFIX_SALE_REMARKS).get());
        }
        return descriptor;
    }
}
