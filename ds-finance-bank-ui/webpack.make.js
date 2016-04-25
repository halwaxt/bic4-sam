'use strict';

// Modules
var webpack = require('webpack');
var autoprefixer = require('autoprefixer');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var ngAnnotatePlugin = require('ng-annotate-webpack-plugin');
var CommonsChunkPlugin = require("webpack/lib/optimize/CommonsChunkPlugin");

//require("ngtemplate-loader");
//require("ng-cache-loader");

var path = require("path");

module.exports = function makeWebpackConfig(options) {
    /**
     * Environment type
     * BUILD is for generating minified builds
     * TEST is for generating test builds
     */
    var BUILD = !!options.BUILD;
    var TEST = !!options.TEST;
    var CONFIG = options.CONFIG || {};

    /**
     * Config
     * Reference: http://webpack.github.io/docs/configuration.html
     * This is the object where all configuration gets set
     */
    var config = {};

    /**
     * Entry
     * Reference: http://webpack.github.io/docs/configuration.html#entry
     * Should be an empty object if it's generating a test build
     * Karma will set this when it's a test build
     */
    if (TEST) {
        config.entry = {}
    } else {
        config.entry = {
            vendor: ["./src/ui/vendor.js"],
            app: ['./src/ui/app.js'],
            /*cards : ["./src/ui/modules/cards/index.js"],
            dashboard : ["./src/ui/modules/dashboard/index.js"],
            exchangeRates : ["./src/ui/modules/exchangeRates/index.js"],
            invoices : ["./src/ui/modules/invoices/index.js"],
            login : ["./src/ui/modules/login/index.js"],
            messages : ["./src/ui/modules/messages/index.js"],
            settings : ["./src/ui/modules/settings/index.js"],
            transactions : ["./src/ui/modules/transactions/index.js"],
            dcUi : ["./src/ui/modules/dc-ui/index.js"],
            dcbEaccount : ["./src/ui/modules/dcb-eaccount/index.js"]*/
        }
    }

    /**
     * Output
     * Reference: http://webpack.github.io/docs/configuration.html#output
     * Should be an empty object if it's generating a test build
     * Karma will handle setting it up for you when it's a test build
     */
    if (TEST) {
        config.output = {}
    } else {
        config.output = {
            // Absolute output directory
            path: __dirname + '/dist',

            // Output path from the view of the page
            // Uses webpack-dev-server in development
            publicPath: BUILD ? '/' : '/',

            // Filename for entry points
            // Only adds hash in build mode
            filename: BUILD ? '[name].[hash].js' : '[name].bundle.js',

            // Filename for non-entry points
            // Only adds hash in build mode
            chunkFilename: BUILD ? '[name].[hash].js' : '[name].bundle.js'
        }
    }

    /**
     * Devtool
     * Reference: http://webpack.github.io/docs/configuration.html#devtool
     * Type of sourcemap to use per build type
     */
    if (TEST) {
        config.devtool = 'inline-source-map';
    } else if (BUILD) {
        config.devtool = 'source-map';
    } else {
        config.devtool = 'source-map';
    }

    config.resolve = {
        extensions: ["", ".js", ".min.js"],
        /*alias : {
         "bower_components" : path.resolve(__dirname,"./bower_components")
         },*/
        root: path.resolve(__dirname),
        alias: {
            jquery: "jquery",
            modules: path.resolve(__dirname, "src/ui/modules"),
            app: path.resolve(__dirname, "src/ui/app"),
            less : path.resolve(__dirname,"src/ui/less")
        },
        fallback: path.join(__dirname, "node_modules"),
        modulesDirectories: ["node_modules", "bower_components"]
    };

    config.resolveLoader = {
        fallback: path.join(__dirname, "node_modules")
    };

    /**
     * Loaders
     * Reference: http://webpack.github.io/docs/configuration.html#module-loaders
     * List: http://webpack.github.io/docs/list-of-loaders.html
     * This handles most of the magic responsible for converting modules
     */

        // Initialize module
    config.module = {
        //noParse: [/moment.js/],
        preLoaders: [
            {test: /\.js$/, loader: "eslint-loader", exclude: [/node_modules/, /\.min\.js$/]}
        ],
        loaders: [
            {test: require.resolve("jquery"), loader: "expose?$!expose?jQuery"},
            {
                test: /\.js$/,
                loaders: ['babel'],
                exclude: /node_modules/
            },
            //{test: /src.*\.js$/, loaders: ['ng-annotate']},
            {
                // ASSET LOADER
                // Reference: https://github.com/webpack/file-loader
                // Copy png, jpg, jpeg, gif, svg, woff, woff2, ttf, eot files to output
                // Rename the file using the asset hash
                // Pass along the updated reference to your code
                // You can add here any file extension you want to get copied to your output
                test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)$/,
                loader: 'file'
            },
            {test: /\.hbs$/, loader: "handlebars-loader"},
            /*{
             test: /\.html$/,
             loader: 'ngtemplate?relativeTo=' + path.resolve(__dirname, './src/ui/') + '!html'
             },*/
            {
                test: /\.test$/,
                loader: 'ngtemplate?relativeTo=' + (path.resolve(__dirname, './src/ui/')) + '/!html!'
            },
            {
                // HTML LOADER
                // Reference: https://github.com/webpack/raw-loader
                // Allow loading html through js
                test: /\.html$/,
                loader: 'raw'
            },
      {
        // HTML LOADER
        // Reference: https://github.com/webpack/raw-loader
        // Allow loading html through js
        test: /\.hb$/,
        loader: 'html'
      },
            //Modernizr Fix
            {
                test: /[\\\/]bower_components[\\\/]modernizr[\\\/]lib[\\\/]cli\.js$/,
                loader: "imports?this=>window!exports?window.Modernizr"
            },
            //Masonry fix
            {
                test: /masonry-layout/,
                loader: 'imports?define=>false&this=>window'
            }
        ],
        //postLoaders : [      {test: /\.js$/, loaders: ['ng-annotate']}]
    };

    // ISPARTA LOADER
    // Reference: https://github.com/ColCh/isparta-instrumenter-loader
    // Instrument JS files with Isparta for subsequent code coverage reporting
    // Skips node_modules and files that end with .test.js
    if (TEST) {
        config.module.preLoaders.push({
            test: /\.js$/,
            exclude: [
                /node_modules/,
                /\.test\.js$/
            ],
            loader: 'isparta-instrumenter'
        })
    }

    // CSS LOADER
    // Reference: https://github.com/webpack/css-loader
    // Allow loading css through js
    //
    // Reference: https://github.com/postcss/postcss-loader
    // Postprocess your css with PostCSS plugins
    var cssLoader = {
        test: /\.css$/,
        // Reference: https://github.com/webpack/extract-text-webpack-plugin
        // Extract css files in production builds
        //
        // Reference: https://github.com/webpack/style-loader
        // Use style-loader in development for hot-loading
        loader: ExtractTextPlugin.extract('style', 'css?sourceMap!postcss')
    };

    // Skip loading css in test mode
    if (TEST) {
        // Reference: https://github.com/webpack/null-loader
        // Return an empty module
        cssLoader.loader = 'null'
    }

    // Add cssLoader to the loader list
    config.module.loaders.push(cssLoader);

    /**
     * PostCSS
     * Reference: https://github.com/postcss/autoprefixer-core
     * Add vendor prefixes to your css
     */
    config.postcss = [
        autoprefixer({
            browsers: ['last 2 version']
        })
    ];

    var lessLoader = {
        test: /\.less$/,
        // Reference: https://github.com/webpack/extract-text-webpack-plugin
        // Extract css files in production builds
        //
        // Reference: https://github.com/webpack/style-loader
        // Use style-loader in development for hot-loading
        loader: ExtractTextPlugin.extract('style', 'css?sourceMap!less!postcss')
    };

    // Skip loading css in test mode
    if (TEST) {
        // Reference: https://github.com/webpack/null-loader
        // Return an empty module
        lessLoader.loader = 'null'
    }

    // Add cssLoader to the loader list
    config.module.loaders.push(lessLoader);

    /**
     * Plugins
     * Reference: http://webpack.github.io/docs/configuration.html#plugins
     * List: http://webpack.github.io/docs/list-of-plugins.html
     */
    config.plugins = [
        // Reference: https://github.com/webpack/extract-text-webpack-plugin
        // Extract css files
        // Disabled when in test mode or not in build mode
        new ExtractTextPlugin('[name].css', {
            disable: TEST
        }),
        new webpack.ProvidePlugin({
            $: "jquery",
            jQuery: "jquery",
            "window.jQuery": "jquery",
            "window.$": "jquery",
            moment: "moment",
            "window.moment": "moment",
            _ : path.resolve(__dirname,"./src/ui/app/externals/lodash"),
            "IntlMessageFormat": "intl-messageformat"
        })
    ];

    // Skip rendering index.html in test mode
    if (!TEST) {
        // Reference: https://github.com/ampedandwired/html-webpack-plugin
        // Render index.html
        config.plugins.push(
            new HtmlWebpackPlugin({
                template: './src/ui/index.html',
                inject: 'body',
                hash: true
            })
        )

        config.plugins.push(
            new HtmlWebpackPlugin({
                template: './src/ui/error.html',
                filename: 'error.html',
                inject: 'body'
            })
        )

        config.plugins.push(new webpack.optimize.OccurenceOrderPlugin());
        config.plugins.push(new webpack.HotModuleReplacementPlugin());
        config.plugins.push(new webpack.NoErrorsPlugin());

        config.plugins.push(new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/));
        //config.plugins.push(new webpack.optimize.UglifyJsPlugin());

        /*config.plugins.push(new ngAnnotatePlugin({
         add: true,
         remove: true,
         stats: true
         // other ng-annotate options here
         }));*/
    }

    // Add build specific plugins
    if (BUILD) {
        config.plugins.push(
            // Reference: http://webpack.github.io/docs/list-of-plugins.html#noerrorsplugin
            // Only emit files when there are no errors
            new webpack.NoErrorsPlugin(),

            // Reference: http://webpack.github.io/docs/list-of-plugins.html#dedupeplugin
            // Dedupe modules in the output
            new webpack.optimize.DedupePlugin(),

            // Reference: http://webpack.github.io/docs/list-of-plugins.html#uglifyjsplugin
            // Minify all javascript, switch loaders to minimizing mode
            new webpack.optimize.UglifyJsPlugin({
              minimize: true,
              mangle: {
                except: ['$q', '$ocLazyLoad']
              }
            })
        )
    }

    /**
     * Dev server configuration
     * Reference: http://webpack.github.io/docs/configuration.html#devserver
     * Reference: http://webpack.github.io/docs/webpack-dev-server.html
     */
    config.devServer = {
        contentBase: './public',
        stats: {
            modules: false,
            cached: false,
            colors: true,
            chunk: false
        }
    };

    return config;
};
