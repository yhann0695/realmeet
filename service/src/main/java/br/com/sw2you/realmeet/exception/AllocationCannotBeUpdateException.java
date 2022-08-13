package br.com.sw2you.realmeet.exception;

import static br.com.sw2you.realmeet.validator.ValidatorConstants.ALLOCATION_ID;
import static br.com.sw2you.realmeet.validator.ValidatorConstants.IN_THE_PAST;

import br.com.sw2you.realmeet.validator.ValidationError;

public class AllocationCannotBeUpdateException extends InvalidRequestException {

    public AllocationCannotBeUpdateException() {
        super(new ValidationError(ALLOCATION_ID, ALLOCATION_ID + IN_THE_PAST));
    }
}
