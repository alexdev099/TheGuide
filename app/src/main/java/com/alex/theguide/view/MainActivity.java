package com.alex.theguide.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.Toast;

import com.alex.theguide.R;
import com.alex.theguide.dagger2.TheGuideApplication;
import com.alex.theguide.dagger2.component.DaggerMainActivityComponent;
import com.alex.theguide.dagger2.component.MainActivityComponent;
import com.alex.theguide.dagger2.module.MainActivityModule;
import com.alex.theguide.model.FileModel;
import com.alex.theguide.presenter.IMainViewPresenter;

import java.util.ArrayList;
import java.util.List;

//For the example, only "FOR WORK" folder and ("ICONS" folder -> in the FOR WORK) has the attached files and folders.

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    //This string is used for Intent
    public static final String EXTRA_STRING = "string for the list";
    private static final String MY_LOG = MainActivity.class.getSimpleName();

    //This field is used as identifier of the folders level.
     private long parentFolderID;

//If our activity will be terminated by rotation screen, than before terminating we will save the current list
// of objects and we wouldn't be need to access the database again.
     private ArrayList<FileModel> currentFileModelList;

     private RecyclerView myRecyclerView;
     private IMainViewPresenter presenter;
     private MyRecyclerViewAdapter myRecyclerViewAdapter;
     private MyDialogFragment dialogFragment;
     private RecyclerItemTouchHelper itemTouchHelperCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            checkIntent();
            initViews();
            creatingMainElements();

            //Sets view to presenter.
            presenter.attachView(this);
            //Retrieves data from the database and then displays it on the screen.
            presenter.viewIsReady(parentFolderID);
        }

        if (savedInstanceState != null){
            initViews();
            creatingMainElements();

            //Sets view to presenter.
            presenter.attachView(this);
            currentFileModelList = savedInstanceState.getParcelableArrayList("savedList");
            showFileModelList(currentFileModelList);
        }

        initSwipe();
        initDialog();
    }

    //checks, if this is the first run of the application then load top level list of the folders
    //else might displayed list of object of the current folder id.
    private void checkIntent() {
        if (getIntent().getLongExtra(EXTRA_STRING, 0) != 0) {
            parentFolderID = getIntent().getLongExtra(EXTRA_STRING, 0);
        }
    }

    //Initialize all main view elements.
    private void initViews() {
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //Initialize all main elements of the activity.
    private void creatingMainElements() {

        //Getting all needed elements with Dagger2
        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(0, ItemTouchHelper.LEFT))
                .applicationComponent(TheGuideApplication.get(this).getTheGuideApplicationComponent())
                .build();
        myRecyclerViewAdapter = component.getMyRecyclerViewAdapter();
        presenter = component.getMainViewPresenter();
        dialogFragment = component.getMyDialogFragment();
        itemTouchHelperCallback = component.getRecyclerItemTouchHelper();

        //Setting Recycler View Adapter and Listeners
        myRecyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(FileModel fileModel) {
                presenter.itemClicked(fileModel);
            }
        });

        myRecyclerViewAdapter.setOnItemLongClickListener(new MyRecyclerViewAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick() {
                presenter.itemLongClicked();
            }
        });
    }

    private void initSwipe() {
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(myRecyclerView);
        itemTouchHelperCallback.setRecyclerItemTouchHelperListener(new RecyclerItemTouchHelper.RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                presenter.itemSwiped();
            }
        });
    }

    private void initDialog() {
        dialogFragment.setOnListItemClickListener(new MyDialogFragment.onListItemClickListener() {
            @Override
            public void onLinkAsFavoriteClick() {
                presenter.linkAsFavoriteClicked();
            }

            @Override
            public void onLinkGetPermalinkClick() {
                presenter.linkGetPermalinkClicked();
            }

            @Override
            public void onLinkDeleteClick() {
                presenter.linkDeleteClicked();
            }
        });
    }

    public void showFileModelList(List<FileModel> fileModelList) {
        myRecyclerViewAdapter.setFileModelsList(fileModelList);

        currentFileModelList = new ArrayList<>();
        currentFileModelList.addAll(0,fileModelList);
    }

    //When user click on the folder, this method creates new activity and put into it folders level name for the current list.
    public void openFolder(Long parentFolderID) {
        Intent folderContent = new Intent(getApplicationContext(), MainActivity.class);
        folderContent.putExtra(EXTRA_STRING, parentFolderID);
        startActivity(folderContent);
    }

    @Override
    public void showMessage(String string) {
        Log.d(MY_LOG, string);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getApplicationContext(), R.string.Error, Toast.LENGTH_LONG).show();
    }

    public void showDialog() {
        dialogFragment.show(getSupportFragmentManager(), "custom");
        myRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList("savedList", currentFileModelList);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
