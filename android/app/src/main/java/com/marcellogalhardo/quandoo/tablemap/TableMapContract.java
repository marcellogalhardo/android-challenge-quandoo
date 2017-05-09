package com.marcellogalhardo.quandoo.tablemap;

import com.marcellogalhardo.quandoo.data.Table;

import java.util.List;

public interface TableMapContract {

    interface Presenter {

        void bindView(TableMapContract.View view);

        void unbindView();

        void getTables();

        void updateTables(List<Table> tables);

    }

    interface View {

        void showErrorLayout();

        void showContentLayout();

        void showLoadingLayout();

        void setTables(List<Table> tables);

        void showUpdateTableSuccessMessage();

        void showUpdateTableFailureMessage();

        void close();

    }

}
