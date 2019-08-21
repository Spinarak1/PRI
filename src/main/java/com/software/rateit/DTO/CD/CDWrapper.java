package com.software.rateit.DTO.CD;

import com.fasterxml.jackson.annotation.JsonView;
import com.software.rateit.DTO.PaginationContext;
import com.software.rateit.DTO.View;

@JsonView(View.Summary.class)
public class CDWrapper {
    private Iterable<CdDTO> cds;
    private PaginationContext context;

    public CDWrapper(Iterable<CdDTO> cds, PaginationContext context) {
        this.cds = cds;
        this.context = context;
    }

    public Iterable<CdDTO> getCds() {
        return cds;
    }

    public void setCds(Iterable<CdDTO> cds) {
        this.cds = cds;
    }

    public PaginationContext getContext() {
        return context;
    }

    public void setContext(PaginationContext context) {
        this.context = context;
    }
}
