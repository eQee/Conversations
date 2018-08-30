package de.eqee.pn.ui.util;

import android.os.SystemClock;

import com.giphy.sdk.core.models.Category;
import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.models.TermSuggestion;
import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListCategoryResponse;
import com.giphy.sdk.core.network.response.ListMediaResponse;
import com.giphy.sdk.core.network.response.ListTermSuggestionResponse;
import com.giphy.sdk.core.network.response.MediaResponse;

import org.whispersystems.libsignal.logging.Log;

import de.eqee.pn.Config;

public final class GiphyUIHelper {
    private GPHApi giphyclient = new GPHApiClient(Config.GIPHY_API_KEY);

    public MediaResponse resultObj;
    public String resultID = null;
    public String resultIDs[] = new String[100];
    public int resultCount = 0;
    public boolean moreResults = false;

    public String categoryIDs[] = new String[100];
    public int categoryCount = 0;
    public boolean moreCategories = false;

    public String subCategoryIDs[] = new String[100];
    public int subCategoryCount = 0;
    public boolean moreSubCategories = false;

    public String termSuggested = null;

    public boolean isComplete = false;
    public boolean inProgress = false;

    public int search(String search, MediaType mt, int start, int stop, RatingType rt, LangType lt) {
        resultIDs = new String[100];
        resultCount = 0;
        moreResults = false;

        giphyclient.search(search, mt.gif, start, stop, rt, lt, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    return;
                } else {
                    if (result.getData() != null) {
                        for (Media gif : result.getData()) {
                            if (resultCount == 100)
                            {
                                moreResults = true;
                                return;
                            }

                            Log.v("giphy", gif.getId());
                            resultIDs[resultCount] = gif.getId();
                            resultCount++;
                        }
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return resultCount;
    }

    public int trending(MediaType mt, int start, int stop, RatingType rt) {
        resultIDs = new String[100];
        resultCount = 0;
        moreResults = false;

        giphyclient.trending(mt.gif, start, stop, rt, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                if (result == null) {
                    return;
                } else {
                    if (result.getData() != null) {
                        for (Media gif : result.getData()) {
                            if (resultCount == 100)
                            {
                                moreResults = true;
                                return;
                            }

                            Log.v("giphy", gif.getId());
                            resultIDs[resultCount] = gif.getId();
                            resultCount++;
                        }
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return resultCount;
    }

    public boolean translate(String translate, MediaType mt, RatingType rt, LangType lt) {
        resultID = null;

        giphyclient.translate(translate, mt, rt, lt, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        Log.v("giphy", result.getData().getId());
                        resultID = result.getData().getId();
                        resultObj = result;
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return resultID != null;
    }

    public boolean random(String tags, MediaType mt, RatingType rt) {
        resultID = null;

        giphyclient.random(tags, mt, rt, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        Log.v("giphy", result.getData().getId());
                        resultID = result.getData().getId();
                        resultObj = result;
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return resultID != null;
    }

    public boolean gifById(String ID) {
        resultID = null;

        if (inProgress) {
            return false;
        }

        inProgress = true;
        isComplete = false;

        Log.v("giphy", "Getting GIF by ID " + ID);

        giphyclient.gifById(ID, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        Log.v("giphy", result.getData().getId());
                        resultID = result.getData().getId();
                        resultObj = result;
                        inProgress = false;
                        isComplete = true;
                    } else {
                        Log.e("giphy error", "No results found");
                        inProgress = false;
                    }
                }
                else
                {
                    inProgress = false;
                }
            }
        });

        return isComplete;
    }

    public int categoriesForGifs(String search, int start, int stop) {
        categoryIDs = new String[100];
        categoryCount = 0;
        moreCategories = false;

        giphyclient.categoriesForGifs(start, stop, search, new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(ListCategoryResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        for (Category category : result.getData()) {
                            if (categoryCount == 100)
                            {
                                moreCategories = true;
                                return;
                            }

                            Log.v("giphy", category.getName());
                            categoryIDs[categoryCount] = category.getName();
                            categoryCount++;
                        }
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return categoryCount;
    }

    public int subCategoriesForGifs(String parentCategory, String search, int start, int stop) {
        subCategoryIDs = new String[100];
        subCategoryCount = 0;
        moreSubCategories = false;

        giphyclient.subCategoriesForGifs(parentCategory, start, stop, search, new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(ListCategoryResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        for (Category category : result.getData()) {
                            if (subCategoryCount == 100)
                            {
                                moreSubCategories = true;
                                return;
                            }

                            Log.v("giphy", category.getName());
                            subCategoryIDs[subCategoryCount] = category.getName();
                            subCategoryCount++;
                        }
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return subCategoryCount;
    }

    public boolean termSuggestions(String search) {
        termSuggested = null;

        giphyclient.termSuggestions(search, new CompletionHandler<ListTermSuggestionResponse>() {
            @Override
            public void onComplete(ListTermSuggestionResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        for (TermSuggestion term : result.getData()) {
                            Log.v("giphy", term.getTerm());
                            termSuggested = term.getTerm();
                        }
                    } else {
                        Log.e("giphy error", "No results found");
                    }
                }
            }
        });

        return termSuggested != null;
    }
}